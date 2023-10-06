package com.newproject.projectn.dto.post;

import com.newproject.projectn.entitiy.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostDtos {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostPostDto {
        long userId;
        String title;
        String body;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PatchDto {

        Long userId;
        Long postId;
        String title;
        String body;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MainResponseDto {

        String title;
        String url;
        LocalDateTime regTime;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseDtoForList {

        String title;
        String url;
        Integer commentNum;
        String writerName;
        int views;
        LocalDateTime regTime;
    }



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Builder
    public static class ResponseDtoForDetailPage {

        long postId;
        long userId;
        String nickName;// 로그인 아이디
        String title;
        String body;
        int recommend;
        int view;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
        List<Comment> commentList ;
    }
}
