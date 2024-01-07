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
@ToString(of = {"income", "carType","carNumber","image"})
public class Driver extends User{
    @Column(nullable = false)
    private int income;
    @Column(nullable = false)
    private String carType;
    @Column(nullable = false)
    private String carNumber;
    private String image;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Call> call = new ArrayList<Call>();

    public Driver(String carType,String carNumber){
        this(0,carType,carNumber,null);
    }

    public Driver(int income, String carType, String carNumber, String image) {
        this.income = income;
        this.carType = carType;
        this.carNumber = carNumber;
        this.image = image;
    }

}
