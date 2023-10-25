package com.newproject.projectn.Service;


import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.dto.chat.ChatMessageDto;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.chat.ChatMessage;
import com.newproject.projectn.entitiy.chat.ChatRoom;
import com.newproject.projectn.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageService {

    private ChatMessageRepository chatMessageRepository;
    private UserService userService;
    private ChatRoomService chatRoomService;

    public ChatMessage sendMessage(ChatMessageDto.PostDto postDto, User user){

        ChatRoom chatRoom = chatRoomService.findChatRoom(postDto.getChatRoomId());
        ChatMessage chatMessage = ChatMessage.builder().message(postDto.getMessage()).chatId(chatRoom.getRoomId()).sendingUser(user).build();
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(Long chatRoomId, User reqUser){
        ChatRoom chatRoom = chatRoomService.findChatRoom(chatRoomId);

        if(chatRoom.getUsers().contains(reqUser)){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return chatMessageRepository.findByChatRoomId(chatRoomId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
    }

    public ChatMessage findMessageById(Long chatMessageId){
        return chatMessageRepository.findById(chatMessageId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
    }

    public void deleteMessage(long chatMessageId, User user) {
        try {
            ChatMessage chatMessage = chatMessageRepository.findById(chatMessageId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
            if (chatMessage.getSendingUser().equals(user)) chatMessageRepository.deleteById(chatMessageId);
        } catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT);
        }
    }
}
