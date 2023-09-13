package com.newproject.projectn.entitiy.address;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long addressId;
    @JoinColumn(name = "city_Id")
    @OneToOne
    private City city;
    @Column
    private String details;
    @Column(length = 5)
    private String zipcode;

    public Address( City city, String details, String zipcode) {

        this.city = city;
        this.details = details;
        this.zipcode = zipcode;
    }


}
