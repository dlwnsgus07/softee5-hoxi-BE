package com.allroundbeauty.server.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DriverAcceptResponseDTO {
    private LocalDateTime arrivalTime;
}
