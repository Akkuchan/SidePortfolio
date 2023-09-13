package com.newproject.projectn.entitiy.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;

    @Column
    String stateName;

    @JsonIgnore
    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    List<City> cities= new ArrayList<>();

    public State(Long stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }
}
