package com.newproject.projectn.Service;

import com.newproject.projectn.entitiy.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    public Post createPost() {
        return new Post();
    }

    public Post findPost() {
        return new Post();

    }

    public List<Post> findPostList() {
        return new ArrayList<>();

    }

    public Post editPost() {
        return new Post();

    }
}
