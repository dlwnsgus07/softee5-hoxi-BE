package com.allroundbeauty.server.service;

import com.allroundbeauty.server.domain.Call;
import com.allroundbeauty.server.dto.CallDTO;
import com.allroundbeauty.server.repository.CallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CallService {
    private final CallRepository callRepository;
    public List<CallDTO> getCallList() {
        List<Call> calls = callRepository.findAll();
        return calls.stream().map(call -> {
                    CallDTO callDTO = new CallDTO();
                    callDTO.setId(call.getId());
                    callDTO.setSource(call.getSource());
                    callDTO.setDestination(call.getDestination());
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

        convertDateTime = convertDateTime.replace("AM", "am").replace("PM", "pm");

        return convertDateTime;
    }
}
