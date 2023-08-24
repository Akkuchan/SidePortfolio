package com.newproject.projectn.entitiy;

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
    String email;
    @Column
    String nickName;
    @Column
    String phoneNumber;
    @Column
    LocalDate registrationDate;// 가입일
    @Column
    LocalDateTime latestLogin; // 최근 로그일
    @Enumerated(value = EnumType.STRING)
    @Column
    String userType; // 유저타입, 학부모, 선생님, 관리자 Enum으로 변경필요
    @Column
    private String image="";

    @OneToMany
    List<Waiting> waitingList;// 최대 3개까지 가능

    @OneToMany
    List<Comment> CommentList;



}
