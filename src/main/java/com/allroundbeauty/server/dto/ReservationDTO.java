package com.allroundbeauty.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDTO {
    private String name;
    private String phoneNumber;
    private String hotelNumber;
}
