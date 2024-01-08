package com.allroundbeauty.server.service;

import com.allroundbeauty.server.domain.user.Driver;
import com.allroundbeauty.server.dto.driverInfo.DriverDetailDto;
import com.allroundbeauty.server.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverDetailDto getDriverInfoById(Long id) {
        return driverRepository.findByDriverDetailDto(id);
    }



    public void addDriver(Driver driver){
        driverRepository.save(driver);
    }
}
