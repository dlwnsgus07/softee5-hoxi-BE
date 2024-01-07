package com.allroundbeauty.server.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.allroundbeauty.server.domain.Call;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("customer")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends User{
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Destination> destinations = new ArrayList<Destination>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Call> call = new ArrayList<Call>();
}
