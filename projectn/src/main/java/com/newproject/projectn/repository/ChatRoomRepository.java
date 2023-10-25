package com.newproject.projectn.repository;

import java.util.*;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select c from ChatRoom c join c.users u where u.id=:userId")
    public List<ChatRoom> findChatRoomByUserId(@Param("userId") Long userId);

    @Query("select c from ChatRoom c where :user Member of c.users And :reqUser Member of c.users")
    public Optional<ChatRoom> findSingleChatByUserIds(@Param("reqUser") User reqUser, @Param("user") User user);
}
