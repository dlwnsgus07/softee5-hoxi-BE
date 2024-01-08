package com.allroundbeauty.server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallCreateVo {
    @JsonProperty(value = "user_id")
    private Long userId;
    private CallVo call;
    private ReservationVo reservation;
}
