package com.newproject.projectn.entitiy;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;

    @ManyToOne
    @JoinColumn(name = "post_user_id")
    User postUser;// 게시글 작성자
    String title;
    String body;
    LocalDateTime postTime; // 게시글 작성 시간
    LocalDateTime EditTime; // 게시글 수정 시간
    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

}
