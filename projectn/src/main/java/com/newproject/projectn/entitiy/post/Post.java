package com.newproject.projectn.entitiy.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn//구분자 컬럼을 말함. 조인 전략과 단일 테이블 전략에서
public class Post extends BaseTimeEntity {//자유소통글, 인기글에 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_user_id")
    User postUser;// 게시글 작성자
    String title;
    String body;
    Integer recommend=11;
    Integer view =0;
    List<String> imgList;// 첨부 이미지
    List<String> tags;//태그 목록


    @OneToMany( mappedBy = "commentedPost", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Comment> commentList = new ArrayList<>();
    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public void setViewPlus1(){
        ++view;
    }

    public void setRecommendPlus(){++recommend;}
}
