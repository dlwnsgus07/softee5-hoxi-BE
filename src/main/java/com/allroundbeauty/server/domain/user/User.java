package com.allroundbeauty.server.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name", "PhoneNumber"})
@Getter @Setter
public abstract class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false    )
    private String PhoneNumber;
}
