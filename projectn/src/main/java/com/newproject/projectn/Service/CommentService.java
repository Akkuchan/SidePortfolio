package com.newproject.projectn.Service;

import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    public Comment createComment() {
        return new Comment();
    }

    public Comment findComment() {
        return new Comment();

    }

    public List<Comment> findCommentList() {
        return new ArrayList<>();
    }

    public Comment editComment() {
        return new Comment();

    }
}
