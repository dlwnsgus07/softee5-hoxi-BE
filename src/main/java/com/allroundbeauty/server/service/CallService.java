package com.allroundbeauty.server.service;

import com.allroundbeauty.server.S3.S3Uploader;
import com.allroundbeauty.server.domain.Call;
import com.allroundbeauty.server.domain.Reservation;
import com.allroundbeauty.server.domain.State;
import com.allroundbeauty.server.domain.user.Customer;
import com.allroundbeauty.server.domain.user.Driver;
import com.allroundbeauty.server.dto.*;
import com.allroundbeauty.server.dto.driverInfo.DriverPositionDto;
import com.allroundbeauty.server.exception.AlreadyAcceptedException;
import com.allroundbeauty.server.exception.BadRequestException;
import com.allroundbeauty.server.exception.NotAcceptedException;
import com.allroundbeauty.server.repository.*;
import com.allroundbeauty.server.vo.CallCreateVo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallService {
    private final CustomerRepository customerRepository;
    private final CallRepository callRepository;
    private final ReservationRepository reservationRepository;
    private final S3Uploader s3Uploader;
    private final S3Repository s3Repository;
    private final DriverRepository driverRepository;

    public Long findDriverIdByCallId(Long callId) {
        return callRepository.findById(callId)
                .orElseThrow(() -> new BadRequestException("Bad Request"))
                .getDriver().getId();
    }

    public DriverPositionDto findDriverPosition(Long id) {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Call not found with id: " + id));
        if (call.getState() == State.PICKUP) {
            try {
                double pos_x = call.getPosition_x();
                double pos_y = call.getPosition_y();
                log.error("pos_x : " + pos_x + "pos_y : " + pos_y);
                return new DriverPositionDto(call.getPosition_x(), call.getPosition_y());
            } catch (NullPointerException e) {
                log.info("call.id : " + call.getId());
                throw new NotAcceptedException("위치정보를 불러올 수 없습니다.");
            }
        } else {
            throw new NotAcceptedException("배차되지 않은 콜입니다.");
        }
    }

    public CallImageDto getCallImage(Long callId) {
        Call call = callRepository.findById(callId).orElseThrow(() -> new BadRequestException("존재하지 않는 호출 입니다."));
        if (call.getState() != State.COMPLETE || call.getDeliveryImage() == null) {
            throw new NotAcceptedException("완료되지 않은 호출 입니다.");
        }
        String imagePath = s3Uploader.getFullImagePath("static", call.getDeliveryImage());
        return new CallImageDto(imagePath);
    }

    @Transactional
    public void updateCallState(Long id) {
        Call call = callRepository.findById(id).orElseThrow(() -> new BadRequestException("존재하지 않는 호출입니다."));
        if (call.getState() == State.COMPLETE && call.getDeliveryImage() != null) {
            call.setState(State.FINISH);
        } else {
            throw new BadRequestException("정상적이지 않은 호출입니다.");
        }
    }

    @Transactional
    public CallPlaceDto createCall(CallCreateVo callCreateVo) {
        Reservation reservation = reservationRepository.save(callCreateVo.getReservation().voToEntity());
        Call call = callRepository.save(callCreateVo.getCall().voToEntity());
        Long customerId = callCreateVo.getUserId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new BadRequestException("존재하지 않는 사용자 입니다."));
        call.setReservation(reservation);
        call.setCustomer(customer);
        return new CallPlaceDto(call.getSource(), call.getDestination());
    }

    public List<CallDTO> getCallList() {
        List<Call> calls = callRepository.findAll();
        List<Call> callsWithNullDriver = calls.stream()
                .filter(call -> call.getState() == State.WAIT)
                .toList();
        return callsWithNullDriver.stream().map(call -> {
                    CallDTO callDTO = new CallDTO();
                    callDTO.setId(call.getId());
                    callDTO.setSource(call.getSource());
                    callDTO.setDestination(call.getDestination());
                    callDTO.setDistance(call.getDistance());
                    callDTO.setCarrier(call.getCarrierNum());
                    callDTO.setDeliveryFee(call.getDeliveryFee());
                    callDTO.setArrivalTime(convertArrivalTime(call.getArrivalTime()));
                    return callDTO;
                })
                .toList();
    }

    private String convertArrivalTime(LocalDateTime arrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");

        String convertDateTime = arrivalTime.format(formatter);
        convertDateTime = convertDateTime.replace("am", "AM").replace("pm", "PM");
        convertDateTime = convertDateTime.replace("오전", "AM").replace("오후", "PM");
        return convertDateTime;
    }

    public CallDetailDTO getCallDetail(Long id) {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("존재하지 않는 콜입니다."));
        return CallDetailDTO.builder()
                .id(call.getId())
                .reservation(ReservationDTO
                        .builder()
                        .name(call.getReservation().getName())
                        .hotelNumber(call.getReservation().getHotelNumber())
                        .phoneNumber(call.getReservation().getPhoneNumber())
                        .build())
                .source(call.getSource())
                .destination(call.getDestination())
                .requirement(call.getRequirement())
                .distance(call.getDistance())
                .arrivalTime(convertArrivalTime(call.getArrivalTime()))
                .carrier(call.getCarrierNum())
                .isCargo(call.isCargo())
                .deliveryFee(call.getDeliveryFee())
                .build();
    }

    @Transactional
    public DriverAcceptResponseDTO accept(DriverAcceptRequestDTO acceptRequestDTO) {
        Call call = callRepository.findById(acceptRequestDTO.getCallId())
                .orElseThrow(() -> new BadRequestException("Bad Request"));
        Driver driver = driverRepository.findById(acceptRequestDTO.getUserId())
                .orElseThrow(() -> new BadRequestException("존재하지 않는 유저입니다."));
        if (call.getState() != State.WAIT) {
            throw new AlreadyAcceptedException("이미 배차된 콜입니다.");
        }
        call.setDriver(driver);
        call.setPosition_x(acceptRequestDTO.getPositionX());
        call.setPosition_y(acceptRequestDTO.getPositionY());
        call.setState(State.PICKUP);
        return DriverAcceptResponseDTO.builder()
                .arrivalTime(call.getArrivalTime())
                .build();
    }

    @Transactional
    public void deliveryCompleted(Long id, MultipartFile image) {
        validateImageFile(image);
        String imageUrl = s3Repository.upload(image);
        callRepository.findById(id)
                .map(call -> {
                    if (call.getState() == State.DELIVERY) {
                        call.setDeliveryImage(imageUrl);
                        call.setState(State.COMPLETE);
                        return call;
                    }
                    throw new BadRequestException("배달중인 콜이 아닙니다.");
                })
                .orElseThrow(() -> new BadRequestException("사용자가 존재하지 않습니다."));
    }

    private void validateImageFile(MultipartFile image) {
        String contentType = image.getContentType();
        if (contentType == null) {
            log.error("파일이 첨부되지 않았습니다");
            throw new BadRequestException("이미지가 첨부되지 않았습니다.");
        }
        if (!contentType.startsWith("image/")) {
            log.info("잘못된 이미지 형식입니다. 현재 이미지 형식 {}", contentType);
            throw new BadRequestException("image 형식이 아닙니다.");
        }
    }

    @Transactional
    public void pickUp(Long id) {
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Bad Request"));
        if (call.getState() == State.PICKUP) {
            call.setState(State.DELIVERY);
            return;
        }
        throw new AlreadyAcceptedException("해당 콜은 대기상태가 아닙니다.");
    }
}