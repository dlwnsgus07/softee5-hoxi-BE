package com.allroundbeauty.server.vo;

import com.allroundbeauty.server.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationVo {
    private String name;
    private String phoneNumber;
    private String hotelNumber;

    public Reservation voToEntity(){
        return Reservation.builder().name(this.getName()).phoneNumber(this.getPhoneNumber())
                .hotelNumber(this.getHotelNumber()).build();
    }
}
