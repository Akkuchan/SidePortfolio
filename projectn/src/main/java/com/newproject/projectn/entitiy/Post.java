package com.newproject.projectn.entitiy;


import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;

    @OneToOne
    @JoinColumn(name = "post_user_id")
    User postUser;// 게시글 작성자
    String title;
    String body;


    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록



    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

}
