package com.newproject.projectn.entitiy;


import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.ap.internal.model.GeneratedType;

import java.util.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enroll extends BaseTimeEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long enrollId;

    LocalDateTime enrollStartTime;
    LocalDateTime enrollEndTime;

    @ManyToOne
    @JoinColumn(name = "kindergarten_Id")
    Kindergarten kindergarten;

    @OneToMany(fetch = FetchType.LAZY)
    List<Waiting> waitingList= new ArrayList<>();




}
