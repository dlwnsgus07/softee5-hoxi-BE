package com.allroundbeauty.server.dto.driverInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverDetailDto {
    private String name;
    private String phoneNumber;
    private String carType;
    private String carNumber;
    private String image;

    public DriverDetailDto(String name, String phoneNumber, String carType, String carNumber, String image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.carType = carType;
        this.carNumber = carNumber;
        this.image = image;
    }
}
