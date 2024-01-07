package com.allroundbeauty.server.domain;

import com.allroundbeauty.server.domain.user.Customer;
import com.allroundbeauty.server.domain.user.Driver;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
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
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    private boolean isCargo;
    @Column(nullable = true)
    private double position_x;
    @Column(nullable = true)
    private double position_y;
}
