package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("select m from ChatMessage m join m.chat_room_id c where c.id=:chatRoomId")
    public Optional<List<ChatMessage>> findByChatRoomId(@Param("chatRoomId") long chatRoomId);
}
