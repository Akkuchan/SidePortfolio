package com.newproject.projectn.entitiy.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn//구분자 컬럼을 말함. 조인 전략과 단일 테이블 전략에서
public class Post extends BaseTimeEntity {//자유소통글, 인기글에 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;
    @ManyToOne
    @JoinColumn(name = "post_user_id")
    User postUser;// 게시글 작성자
    String title;
    String body;
    Integer recommend;
    Integer view;
    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록


    @OneToMany(fetch = FetchType.LAZY)
    List<Comment> commentList = new ArrayList<>();
    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

}
