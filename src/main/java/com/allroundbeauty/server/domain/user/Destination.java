package com.allroundbeauty.server.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "customer"})
public class Destination {
    @Id @GeneratedValue()
    @Column(name = "destination_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Destination(String name){
        this(name,null);
    }

    public Destination(String name, Customer user){
        this.name = name;
        if(user!=null){
            changeUser(user);
        }
    }

    public void changeUser(Customer user){
        this.customer = user;
        user.getDestinations().add(this);
    }
}
