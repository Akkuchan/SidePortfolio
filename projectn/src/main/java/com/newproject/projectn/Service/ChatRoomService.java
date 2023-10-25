package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.dto.chat.ChatRoomDto;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.chat.ChatRoom;
import com.newproject.projectn.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Data
@Service
@AllArgsConstructor
public class ChatRoomService {

    private ChatRoomRepository chatRoomRepository;
    private UserService userService;

    public ChatRoom createSingleRoom(User reqUser, long userId) {
        User user = userService.findUser(userId);
        ChatRoom isChatExist = chatRoomRepository.findSingleChatByUserIds(reqUser, user).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        ChatRoom newChatRoom = new ChatRoom();
        newChatRoom.setName("test");

        newChatRoom.getUsers().add(user);
        newChatRoom.getUsers().add(reqUser);

        return null;



    }

    public ChatRoom findChatRoom(long chatRoomId){
        return chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));

    }

    public  List<ChatRoom> findAllChatRoom(long userId){
        User user = userService.findUser(userId);
        List<ChatRoom> chatRoomList = chatRoomRepository.findChatRoomByUserId(userId);
        return chatRoomList;
    }

    public ChatRoom createGroupRoom( ChatRoomDto.GroupChatPostDto groupChatPostDto){

        Set<User> users = userService.findUserListByIdList(groupChatPostDto.getInvitedUsersIdList());
        ChatRoom groupChatRoom = new ChatRoom();
        groupChatRoom.setName(groupChatPostDto.getRoomName());
        groupChatRoom.setUsers(users);
        return groupChatRoom;
    }
    public ChatRoom addUserToGroup(long userId, long chatRoodId){
        ChatRoom foundChatRoom = chatRoomRepository.findById(chatRoodId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        User user = userService.findUser(userId);
        foundChatRoom.getUsers().add(user);
        return chatRoomRepository.save(foundChatRoom);

    }

    public ChatRoom renameGroup(long chatRoodId, String newGroupName, User reqUser){

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoodId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        if(chatRoom.getUsers().contains(reqUser)){
            chatRoom.setName(newGroupName);
            return chatRoomRepository.save(chatRoom);
        }else{
            throw new BusinessLogicException(ExceptionCode.INVALID_AUTH_TOKEN);
        }

    }

    public ChatRoom leaveFromGroup(long chatRoodId, long userId, long reqUserId){
        User leavingUser = userService.findUser(userId);
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoodId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        chatRoom.getUsers().remove(leavingUser);
        if(chatRoom.getUsers().size()==0){
            chatRoomRepository.delete(chatRoom);
            return null;
        }else {
           return  chatRoomRepository.save(chatRoom);
        }
    }
    public void deleteChat(long chatRoodId){// 강제 채팅방 삭제
        chatRoomRepository.deleteById(chatRoodId);
    }






//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try{
//            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}