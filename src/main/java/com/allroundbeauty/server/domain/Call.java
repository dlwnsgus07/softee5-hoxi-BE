package com.allroundbeauty.server.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Driver driver;
    private String source;
    private String destination;
    private LocalDateTime arrivalTime;
    private double distance;
    private String requirement;
    private int deliveryFee;

    @Enumerated(EnumType.STRING)
    private State state;

    private String deliveryImage;
    private int carrierNum;
    @OneToOne
    private Reservation reservation;
    private boolean isCargo;
}
