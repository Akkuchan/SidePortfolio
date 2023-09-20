package com.newproject.projectn.entitiy.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newproject.projectn.entitiy.post.LocalInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cityId;

    String cityName;

//    @JsonIgnore
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "state_Id")
    State state;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    List<Address> addressList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    List<LocalInfo> localInfoList= new ArrayList<>();
}
