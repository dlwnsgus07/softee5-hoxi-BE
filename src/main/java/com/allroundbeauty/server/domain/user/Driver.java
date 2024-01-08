package com.allroundbeauty.server.domain.user;

import com.allroundbeauty.server.domain.Call;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("driver")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "phoneNumber","income", "carType","carNumber","image"})
public class Driver{

    @Id @GeneratedValue
    @Column(name = "driver_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int income;

    @Column(nullable = false)
    private String carType;

    @Column(nullable = false)
    private String carNumber;

    private String image;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Call> call = new ArrayList<Call>();

    public Driver(String name, String phoneNumber, String carType,String carNumber){
        this(name,phoneNumber,0,carType,carNumber,null);
    }

    public Driver(String name, String phoneNumber, int income, String carType, String carNumber, String image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.income = income;
        this.carType = carType;
        this.carNumber = carNumber;
        this.image = image;
    }

}
