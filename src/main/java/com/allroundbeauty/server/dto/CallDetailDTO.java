package com.allroundbeauty.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CallDetailDTO {
    private Long id;
    private ReservationDTO reservation;
    private String source;
    private String destination;
    private String requirement;
    private double distance;
    private String arrivalTime;
    private int carrierNum;
    @JsonProperty(value = "isCargo")
    private boolean isCargo;
    private int deliveryFee;
}
