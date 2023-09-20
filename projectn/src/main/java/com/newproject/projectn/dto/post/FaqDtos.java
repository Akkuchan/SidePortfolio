package com.newproject.projectn.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FaqDtos {



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDto extends PostPostDto{
        String question;
        String answer;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PatchDto extends PatchPostDto{
        String question;
        String answer;
    }



}
