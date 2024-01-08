package com.allroundbeauty.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallCreateVo {
    private UserIdVo UserId;
    private CallVo call;
    private ReservationVo reservation;
}
