package com.newproject.projectn.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {

    Long userId;
    Long postId;
    String title; //제목
    String body; //댓글 내용
}
