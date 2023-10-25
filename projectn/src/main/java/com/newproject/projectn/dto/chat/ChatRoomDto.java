package com.newproject.projectn.dto.chat;

import lombok.*;
import java.util.List;

public class ChatRoomDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostDto{
        private Long reqUserId; // 채팅방 만드는 사람
        private Long invitedUserId;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PatchNameDto{
        private Long roomId;
        private String newRoomName;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LeaveChatRoomDto{
        private Long roomId; // 나가려는 채팅방
        private Long userId; // 나갈 사람ID

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GroupChatPostDto{

        private Long reqUserId;
        private List<Long> invitedUsersIdList;// 자기 자신의 id도 포함시켜야 함
        private String roomName;
        private String message; // 메시지

    }
}
