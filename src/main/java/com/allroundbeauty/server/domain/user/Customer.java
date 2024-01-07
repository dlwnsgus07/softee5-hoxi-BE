package com.allroundbeauty.server.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.allroundbeauty.server.domain.Call;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "phoneNumber"})
public class Customer{
    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Destination> destinations = new ArrayList<Destination>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Call> call = new ArrayList<Call>();
}
