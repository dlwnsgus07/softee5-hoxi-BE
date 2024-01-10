package com.allroundbeauty.server.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CallDTO {
    private Long id;
    private String source;
    private String destination;
    private double distance;
    private String arrivalTime;
    private int carrierNum;
    private int deliveryFee;
    private Boolean isCargo;
}
