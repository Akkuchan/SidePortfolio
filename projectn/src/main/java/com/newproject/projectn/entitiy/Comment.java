package com.newproject.projectn.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newproject.projectn.entitiy.basetime.BaseTimeEntity;
import com.newproject.projectn.entitiy.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long commentId;
    @ManyToOne
    @JoinColumn(name = "commenting_user_id")
    User commentingUser;
    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL )
    @JoinColumn(name = "commented_post_id")
    Post commentedPost;

    int recommend = 0;

    String body; //댓글 내용

    public Post getCommentedPost() {
        return commentedPost;
    }

    public void setCommentedPost(Post commentedPost) {
        this.commentedPost = commentedPost;
    }


    public User getCommentingUser() {
        return commentingUser;
    }

    public void setCommentingUser(User commentingUser) {
        this.commentingUser = commentingUser;
    }

    public void setRecommendPlus(){++recommend;}


}
