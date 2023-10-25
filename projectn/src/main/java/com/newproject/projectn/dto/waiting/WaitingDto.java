package com.newproject.projectn.dto.waiting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

public class WaitingDto {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto{
        Long userId;
        long enrollId;

        ParentDto parentInfo;
        List<EmergencyDto> emergencyInfos;
        ChildDto childInfo;
//
//    List<String> document1;
//    List<String> document2;
//    Integer enrollRankingGrade;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentDto{
        String username;
        String relationship;
        String phone;
        String address;
        String family;//임시
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmergencyDto{
        String name;
        String relationship;
        String phone;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChildDto{
        String name;
        String childSex;
        String phone;
        String birthDay;
        String health;
//        String remark;
        String childExperience;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseWaitingDto{
        long waitingListId;
        long userId;
        long kindergartenId;
        List<String> document1;
        List<String> document2;
        int enrollRankingGrade;
        LocalDateTime startTime;//신청시작 시간
        LocalDateTime endTime;//신청마감 시간
    }



}
