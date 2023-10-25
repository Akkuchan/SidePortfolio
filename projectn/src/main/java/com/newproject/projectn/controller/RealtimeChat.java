package com.newproject.projectn.controller;

import com.newproject.projectn.entitiy.chat.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class RealtimeChat {

    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("group/public")
    public ChatMessage receiveMessage(@Payload ChatMessage message){
        simpMessagingTemplate.convertAndSend("/group/" + message.getChatRoom().getRoomId(), message);
        return message;
    }
}
