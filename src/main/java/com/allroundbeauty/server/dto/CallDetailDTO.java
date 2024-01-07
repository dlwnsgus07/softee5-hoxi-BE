package com.allroundbeauty.server.dto;

import com.allroundbeauty.server.domain.Reservation;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CallDetailDTO {
    private Long id;
    private Reservation reservation;
    private String source;
    private String destination;
    private String requirement;
    private double distance;
    private String arrivalTime;
    private int carrier;
    private boolean isCargo;
    private int deliveryFee;
}
