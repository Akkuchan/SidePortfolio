package com.newproject.projectn.entitiy;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Waiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long waitingListId;

    @OneToOne
    Kindergarten kindergarten;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    LocalDate expectingEnrollDate;// 입학 희망일
    List<String> document1;
    List<String> document2;
    int enrollRankingGrade; // 입소 순위 배점

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
