package com.newproject.projectn.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long commentId;
    User commentingUser;
    String title; //제목
    String body; //댓글 내용
    LocalDateTime commentTime; //작성시간


}
