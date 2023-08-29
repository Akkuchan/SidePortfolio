package com.newproject.projectn.entitiy;

import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Waiting extends BaseTimeEntity {// 유치원 대기자 목록 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long waitingListId;

    @OneToOne
    private Kindergarten kindergarten;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate expectingEnrollDate;// 입학 희망일
    private List<String> document1;
    private List<String> document2;
    private int enrollRankingGrade; // 입소 순위 배점

    public User getUser() {
        return user;
    }

    public Kindergarten getKindergarten() {
        return kindergarten;
    }

    public int getEnrollRankingGrade() {
        return enrollRankingGrade;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setKindergarten(Kindergarten kindergarten) {
        this.kindergarten = kindergarten;
    }
}
