package com.newproject.projectn.controller;


import com.newproject.projectn.Service.ChatMessageService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.dto.chat.ChatMessageDto;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
@Slf4j
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessageDto.PostDto postDto, @RequestHeader("Authorization") String token){
        User user = userService.findByUserProfile(token);
        ChatMessage chatMessage = chatMessageService.sendMessage(postDto, user);
        return new ResponseEntity(chatMessage, HttpStatus.OK);
    }


    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<ChatMessage>> getMessage(@PathVariable Long chatId, @RequestHeader("Authorization") String token){
        User user = userService.findByUserProfile(token);
        List<ChatMessage> chatMessageList = chatMessageService.findChatMessages(chatId, user);
        return new ResponseEntity(chatMessageList, HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long messageId, @RequestHeader("Authorization") String token){
        User user = userService.findByUserProfile(token);
        chatMessageService.deleteMessage(messageId, user);
        return new ResponseEntity("삭제되었습니다.", HttpStatus.OK);
    }

}
