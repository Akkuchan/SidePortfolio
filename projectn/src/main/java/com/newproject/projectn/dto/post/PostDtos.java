package com.newproject.projectn.dto.post;

import com.newproject.projectn.dto.comment.CommentResponseDto;
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
        long postId;
        String title;
        String url;
        int recommend;
        int commentNum;
        int view;
        String author;
        LocalDateTime regTime;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseDtoForList {
        long postId;
        String title;
        String url;
        int commentNum;
        String writerName;
        int recommend;
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
        LocalDateTime regTime;
        List<CommentResponseDto> commentResponseDtoList;
    }
}
