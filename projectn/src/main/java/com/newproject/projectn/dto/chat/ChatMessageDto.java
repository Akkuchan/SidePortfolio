package com.newproject.projectn.dto.chat;

import lombok.*;

public class ChatMessageDto {


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostDto{
        private Long userId;
        private Long chatRoomId;
        private String message;

    }
}
