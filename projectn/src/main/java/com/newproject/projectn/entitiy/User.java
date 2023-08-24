package com.newproject.projectn.entitiy;

import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.Enum.address.Address;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    @Column
    private String email;
    @Column
    private String nickName;
    @Column
    private String password;
    @Column
    private String phoneNumber;

    @Column
    private LocalDate registrationDate;// 가입일
    @Column
    private LocalDateTime latestLogin; // 최근 로그일
    @Column
    private String image;//프로필 이미지

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserGrade userType; // 유저타입, 학부모, 선생님, 관리자 Enum으로 변경필요


    @OneToOne
    @JoinColumn(name = "address_ID")
    private Address address;


    private boolean isMarried;// 기혼여부
    private boolean isPregnant;// 임신여부
    private boolean hasChild;// 자녀여부

    @OneToMany
    private List<Waiting> waitingList;// 최대 3개까지 가능

    @OneToMany
    private List<Comment> CommentList;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
