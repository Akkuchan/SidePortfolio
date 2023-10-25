package com.newproject.projectn.dto.recommend;

import lombok.*;
import org.springframework.stereotype.Service;

public class RecommendDto {



    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class plusPostRecommend{
        long userId;
        long postId;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class plusCommentRecommend{
        long userId;
        long commentId;
    }

}

