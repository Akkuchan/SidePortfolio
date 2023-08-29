package com.newproject.projectn.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatchCommentDto {
    Long commentId;
    Long postId;
    Long userId;
    String title; //제목
    String body; //댓글 내용

}
