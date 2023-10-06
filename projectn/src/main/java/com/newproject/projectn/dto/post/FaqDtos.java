package com.newproject.projectn.dto.post;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


public class FaqDtos {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto extends  PostDtos.PostPostDto{
        String question;
        String answer;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class PatchDto extends  PostDtos.PatchDto{
        String question;
        String answer;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    public static class ResponseDtoForDetailPage {
        long userId;
        String nickName;
        LocalDateTime regTime;//등록시간
        LocalDateTime updateTime;//수정시간
        String title;
        String body;
        Integer recommend;
        Integer view;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
        String question;
        String answer;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ListResponseDto {
        LocalDateTime regTime;//등록시간
        String title;
        String body;
        Integer recommend;
        Integer view;
    }







    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MainResponseDto {
        LocalDateTime updateTime;//수정시간
        String title;
        String body;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
        String question;
        String answer;
    }


}
