package com.newproject.projectn.entitiy;

import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Waiting extends BaseTimeEntity {// 유치원 대기자 목록 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long waitingListId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate expectingEnrollDate;// 입학 희망일
    private List<String> document1;
    private List<String> document2;
    private int enrollRankingGrade; // 입소 순위 배점

    private LocalDateTime startTime;//신청시작 시간
    private LocalDateTime endTime;//신청마감 시간

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    Enroll enroll;

    public User getUser() {
        return user;
    }



    public int getEnrollRankingGrade() {
        return enrollRankingGrade;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
