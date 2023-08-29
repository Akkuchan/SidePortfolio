package com.newproject.projectn.entitiy.Enum.address;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long addressId;

    @Enumerated(value = EnumType.STRING)
    @Column
    private  State state;
    @Column
    private String city;
    @Column(length = 5)
    private String zipcode;

    public Address(State state, String city, String zipcode) {
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
    }
}
