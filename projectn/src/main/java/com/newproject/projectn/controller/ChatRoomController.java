package com.newproject.projectn.controller;

import com.newproject.projectn.Service.ChatRoomService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.config.security.auth.jwt.JwtService;
import com.newproject.projectn.dto.chat.ChatRoomDto;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatRoomController {

    private final ChatRoomService chatService;
    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/create/single")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoomDto.PostDto postDto){

        ChatRoom newSingleChatRoom = chatService.createSingleRoom(userService.findUser(postDto.getReqUserId()),postDto.getInvitedUserId());
        return new ResponseEntity<>(newSingleChatRoom, HttpStatus.OK);
   }
    @PostMapping("/create/group")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoomDto.GroupChatPostDto groupChatPostDto, @RequestHeader("Authorization") String jwt){

        userService.findByUserProfile(jwt);// 유저가 있는지 체크
        ChatRoom newSingleChatRoom = chatService.createGroupRoom(groupChatPostDto);
        return new ResponseEntity<>(newSingleChatRoom, HttpStatus.OK);
    }
    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoom> enterRoom(@PathVariable long roomId,  @RequestHeader("Authorization") String jwt){
        userService.findByUserProfile(jwt);// 유저가 있는지 체크
        ChatRoom chatRoom =  chatService.findChatRoom(roomId);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<ChatRoom>> findAllRoom(@RequestHeader("Authorization") String jwt) {
         User user = userService.findByUserProfile(jwt);// 유저가 있는지 체크
        List<ChatRoom> chatRoom = chatService.findAllChatRoom(user.getUserId());
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @PatchMapping("/{chatId}/add/{userId}")
    public ResponseEntity<ChatRoom> addMemberInChatRoom(@PathVariable long chatId, @PathVariable long userId, @RequestHeader("Authorization") String jwt) {
        User user = userService.findByUserProfile(jwt);// 유저가 있는지 체크
        ChatRoom chatRoom = chatService.addUserToGroup(user.getUserId(), chatId);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @PatchMapping("/rename")
    public ResponseEntity<ChatRoom> editChatRoomName( @RequestBody ChatRoomDto.PatchNameDto patchNameDto, @RequestHeader("Authorization") String jwt) {
        User user = userService.findByUserProfile(jwt);// 유저가 있는지 체크
        ChatRoom chatRoom = chatService.renameGroup(patchNameDto.getRoomId(), patchNameDto.getNewRoomName(), user);
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @PatchMapping("/leave")
    public ResponseEntity<ChatRoom> leaveChatRoom(@RequestBody ChatRoomDto.LeaveChatRoomDto leaveChatRoomDto, @RequestHeader("Authorization") String jwt) {
        User user = userService.findByUserProfile(jwt);// 유저가 있는지 체크
        ChatRoom chatRoom = chatService.leaveFromGroup(leaveChatRoomDto.getRoomId(),leaveChatRoomDto.getUserId(), user.getUserId());
        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<String> deleteChatRoom(@PathVariable long chatId, @RequestHeader("Authorization") String jwt) {
        userService.findByUserProfile(jwt);// 유저가 있는지 체크
        chatService.deleteChat(chatId);
        return new ResponseEntity<>("삭제되었습니다",HttpStatus.OK);
    }

}