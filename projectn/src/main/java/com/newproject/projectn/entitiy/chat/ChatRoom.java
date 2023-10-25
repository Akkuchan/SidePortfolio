package com.newproject.projectn.entitiy.chat;

import com.newproject.projectn.entitiy.User;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue
    private Long roomId; // 채팅방 아이디
    private String name; // 채팅방 이름

    @ManyToMany
    private Set<User> users = new HashSet<>();
    @OneToMany
    private List<ChatMessage> messageList = new ArrayList<>();


}
