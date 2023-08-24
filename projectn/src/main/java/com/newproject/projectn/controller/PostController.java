package com.newproject.projectn.controller;

import com.newproject.projectn.Service.PostService;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class PostController {

    PostService postService;


    @PostMapping
    public ResponseEntity<Post> postKindergarten(@RequestBody Kindergarten kindergarten){

        Post post = postService.createPost();

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Post> getKindergarten(){

        Post post = postService.findPost();

        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Post>> getKindergartenList(){

        List<Post> postList = postService.findPostList();

        return new ResponseEntity<>(postList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Post> patchKindergarten(){
        Post post = postService.editPost();
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
}
