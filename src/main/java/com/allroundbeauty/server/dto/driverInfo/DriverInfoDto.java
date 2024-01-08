package com.allroundbeauty.server.dto.driverInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverInfoDto {
    private DriverDetailDto info;
    private DriverPositionDto position;
}
