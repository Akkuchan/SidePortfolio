package com.newproject.projectn.controller;

import com.newproject.projectn.Service.PostService;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class PostController {

    PostService postService;


    @PostMapping
    public ResponseEntity<Post> postKindergarten(@RequestBody Kindergarten){

        Post post = postService.createPost();

        return new ResponseEntity<kindergartenList>();
    }

    @GetMapping
    public ResponseEntity<Post> getKindergarten(){

        Post post = postService.findPost();

        return new ResponseEntity<Post>();
    }

    @GetMapping
    public ResponseEntity<Post> getKindergartenList(){

        List<Post> Post = postService.findPostList();

        return new ResponseEntity<Post>();
    }

    @PatchMapping
    public ResponseEntity<Post> patchKindergarten(){
        Post post = postService.editPost();
        return new ResponseEntity<Post>();
    }
}
