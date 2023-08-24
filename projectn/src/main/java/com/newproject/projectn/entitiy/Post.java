package com.newproject.projectn.entitiy;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;

    User postUser;// 게시글 작성자
    String title;
    String body;
    LocalDateTime postTime; // 게시글 작성 시간
    LocalDateTime EditTime; // 게시글 수정 시간
    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록

}
