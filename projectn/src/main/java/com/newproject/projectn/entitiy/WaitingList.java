package com.newproject.projectn.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.*;

@Entity
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long waitingListId;

    Kindergarten kindergarten;
    User user;
    LocalDate expectingEnrollDate;// 입학 희망일
    List<String> document1;
    List<String> document2;
    int enrollRankingGrade; // 입소 순위 배점

}
