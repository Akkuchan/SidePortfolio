package com.newproject.projectn.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    String email;
    String nickName;
    String phoneNumber;
    LocalDate registrationDate;// 가입일
    LocalDateTime latestLogin; // 최근 로그일
    String userType; // 유저타입, 학부모, 선생님, 관리자 Enum으로 변경필요
    List<WaitingList> waitingLists;// 최대 3개까지 가능



}
