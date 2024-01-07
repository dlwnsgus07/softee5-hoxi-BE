package com.allroundbeauty.server.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverAcceptRequestDTO {
    private Long userId;
    private Long callId;
    private double positionX;
    private double positionY;
}
