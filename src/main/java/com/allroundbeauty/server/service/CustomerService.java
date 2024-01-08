package com.allroundbeauty.server.service;

import com.allroundbeauty.server.domain.user.Customer;
import com.allroundbeauty.server.domain.user.Destination;
import com.allroundbeauty.server.dto.DestinationDto;
import com.allroundbeauty.server.exception.BadRequestException;
import com.allroundbeauty.server.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public DestinationDto convertToDto(List<Destination> destinations){
        DestinationDto destinationDto = new DestinationDto();
        destinationDto.setNames(destinations.stream().map(Destination::getName).collect(Collectors.toList()));

        return destinationDto;
    }
    public DestinationDto getRecents(Long customerId) {
        List<Destination> res = customerRepository.findById(customerId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자 입니다.")).getDestinations();
        return convertToDto(res);
    }



}
