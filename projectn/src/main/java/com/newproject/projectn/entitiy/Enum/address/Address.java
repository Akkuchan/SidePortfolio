package com.newproject.projectn.entitiy.Enum.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
