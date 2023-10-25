package com.newproject.projectn.entitiy.chat;

import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chatId;
    private String message; // 메시지

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom; // 방 번호

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sendingUser; // 채팅을 보낸 사람

    // 메시지  타입 : 입장, 채팅
}
