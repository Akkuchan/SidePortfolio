package com.newproject.projectn.dto.post;

import com.newproject.projectn.entitiy.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class LocalInfoDtos {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto extends  PostDtos.PostPostDto{
        Long cityId;
    }

    @Getter
    public static class PatchDto extends  PostDtos.PatchDto{
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
    @Builder
    @Setter
    public static class ResponseDtoForDetailPage {
        Long cityId;
        String cityName;
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
