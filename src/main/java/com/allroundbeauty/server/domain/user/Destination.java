package com.allroundbeauty.server.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Destination {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
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
