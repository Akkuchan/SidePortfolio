package com.newproject.projectn.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    long commentId;
    long postId;
    long userId;
    String userNickName;
    LocalDateTime regTime;
    String body;
    long recommend;


}
