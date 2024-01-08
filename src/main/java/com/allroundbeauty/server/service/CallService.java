package com.allroundbeauty.server.service;

import com.allroundbeauty.server.S3.S3Uploader;
import com.allroundbeauty.server.domain.Call;
import com.allroundbeauty.server.domain.Reservation;
import com.allroundbeauty.server.domain.State;
import com.allroundbeauty.server.domain.user.Customer;
import com.allroundbeauty.server.dto.CallImageDto;
import com.allroundbeauty.server.dto.CallPlaceDto;
import com.allroundbeauty.server.dto.driverInfo.DriverPositionDto;
import com.allroundbeauty.server.exception.BadRequestException;
import com.allroundbeauty.server.exception.NotAcceptedException;
import com.allroundbeauty.server.repository.CallRepository;
import com.allroundbeauty.server.repository.CustomerRepository;
import com.allroundbeauty.server.repository.ReservationRepository;
import com.allroundbeauty.server.util.ApiResponse;
import com.allroundbeauty.server.vo.CallCreateVo;
import com.allroundbeauty.server.vo.ReservationVo;
import com.amazonaws.services.s3.AmazonS3Client;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallService {
    private final CustomerRepository customerRepository;
    private final CallRepository callRepository;
    private final ReservationRepository reservationRepository;
    private final S3Uploader s3Uploader;

    public Long findDriverIdByCallId(Long callId) {
        return  callRepository.findById(callId)
                .orElseThrow(()->new BadRequestException("Bad Request"))
                .getDriver().getId();
    }

    public DriverPositionDto findDriverPosition(Long id){
        Call call = callRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Call not found with id: " + id));
        if(call.getState() == State.PICKUP) {
            try{
                double pos_x = call.getPosition_x();
                double pos_y = call.getPosition_y();
                log.error("pos_x : "+pos_x + "pos_y : " +pos_y);
                return new DriverPositionDto(call.getPosition_x(), call.getPosition_y());
            }
            catch (NullPointerException e){
                log.info("call.id : " + call.getId());
                throw new NotAcceptedException("위치정보를 불러올 수 없습니다.");

            }

        }
        else{
            throw new NotAcceptedException("배차되지 않은 콜입니다.");
        }
    }

    public CallImageDto getCallImage(Long callId) {
        Call call = callRepository.findById(callId).orElseThrow(()->new BadRequestException("존재하지 않는 호출 입니다."));
        if(call.getState() != State.COMPLETE || call.getDeliveryImage() == null){
            throw new NotAcceptedException("완료되지 않은 호출 입니다.");
        }
        String imagePath = s3Uploader.getFullImagePath("static",call.getDeliveryImage());
        return new CallImageDto(imagePath);
    }

    @Transactional
    public void updateCallState(Long id) {
        Call call = callRepository.findById(id).orElseThrow(()->new BadRequestException("존재하지 않는 호출입니다."));

        if(call.getState() == State.COMPLETE && call.getDeliveryImage() != null){
            call.setState(State.FINISH);
        }
        else{
            throw new BadRequestException("정상적이지 않은 호출입니다.");
        }

    }

    @Transactional
    public CallPlaceDto createCall(CallCreateVo callCreateVo) {
        Reservation reservation = reservationRepository.save(callCreateVo.getReservation().voToEntity());
        Call call = callRepository.save(callCreateVo.getCall().voToEntity());
        Long customerId = callCreateVo.getUserId();
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자 입니다."));
        call.setReservation(reservation);

        call.setCustomer(customer);

        return new CallPlaceDto(call.getSource(),call.getDestination());
    }
}
