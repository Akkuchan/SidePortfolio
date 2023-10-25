package com.newproject.projectn.dto.enroll;

import com.newproject.projectn.dto.kindergarten.ResponseKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class EnrollDtos {



    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostEnrollDto {

        LocalDateTime enrollStartTime;
        LocalDateTime enrollEndTime;
        String kindergartenName;
        Long kindergartenId;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatchEnrollStartTimeDto {
        Long enrollId;
        LocalDateTime enrollStartTime;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PatchEnrollEndTimeDto {
        Long enrollId;
        LocalDateTime enrollEndTime;
    }




    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    public static class ResultForMain{
        long enrollId;
        String title;
        String dueTime;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResultForList   {
        long enrollId;
        String title;
        String enrollDate;
        boolean closed;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResultDto   {
        long enrollId;
        ResponseKindergartenDto responseKindergartenDto;
        LocalDateTime enrollStartTime;
        LocalDateTime enrollEndTime;

    }

}
