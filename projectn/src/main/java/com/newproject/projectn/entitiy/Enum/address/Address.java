package com.newproject.projectn.entitiy.Enum.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long addressId;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20)
    private State state;
    @Column(length = 20)
    private String city;
    @Column(length = 6)
    private String zipcode;

}
