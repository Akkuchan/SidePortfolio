package com.newproject.projectn.dto.post;

import com.newproject.projectn.entitiy.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ThriftShopProductDtos {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto extends PostDtos.PostPostDto {
        String productName;// 제품명
        String howToDeliver;// 거래방식, 착불, 직거래
        int price;
        Boolean onSale;// 판매중 or 마감
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PatchDto extends PostDtos.PatchDto{
        String productName;// 제품명
        String howToDeliver;// 거래방식, 착불, 직거래
        int price;
        Boolean onSale;// 판매중 or 마감
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

        long postId;
        long userId;
        String nickName;
        String title;
        String body;
        int recommend;
        int view;
        List<String> imgList;// 첨부 이미지
        List<String> tags;//태그 목록
        List<Comment> commentList ;
        String productName;// 제품명
        String howToDeliver;// 거래방식, 착불, 직거래
        int price;
        Boolean onSale;// 판매중 or 마감
    }


}
