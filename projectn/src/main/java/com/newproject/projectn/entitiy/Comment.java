package com.newproject.projectn.entitiy;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long commentId;
    @ManyToOne
    @JoinColumn(name = "commenting_user_id")
    User commentingUser;
    String title; //제목
    String body; //댓글 내용
    LocalDateTime commentTime; //작성시간

    public User getCommentingUser() {
        return commentingUser;
    }

    public void setCommentingUser(User commentingUser) {
        this.commentingUser = commentingUser;
    }


}
