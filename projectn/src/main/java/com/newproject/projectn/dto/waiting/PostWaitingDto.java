package com.newproject.projectn.dto.waiting;

import com.newproject.projectn.entitiy.Kindergarten;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWaitingDto {
    Long userId;
    Long kindergartenId;
    LocalDate expectingEnrollDate;
    List<String> document1;
    List<String> document2;
    Integer enrollRankingGrade;
}
