package com.newproject.projectn.entitiy.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
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
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long addressId;
    @JoinColumn(name = "city_Id")
    @ManyToOne(fetch =FetchType.LAZY)
   @JsonIgnore
    private City city;
    @Column
    private String details;
    @Column(length = 5)
    private String zipcode;

    @OneToOne(mappedBy = "address")
    private Kindergarten kindergarten;


    public Address( City city, String details, String zipcode) {

        this.city = city;
        this.details = details;
        this.zipcode = zipcode;
    }



}
