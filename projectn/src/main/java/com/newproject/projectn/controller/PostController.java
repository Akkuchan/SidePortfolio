package com.newproject.projectn.controller;

import com.newproject.projectn.Service.PostService;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("post")
@AllArgsConstructor

public class PostController {

    PostService postService;


    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody Kindergarten kindergarten){

        Post post = postService.createPost();

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Post> getPost(){

        Post post = postService.findPost();

        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Post>> getPostList(){

        List<Post> postList = postService.findPostList();

        return new ResponseEntity<>(postList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Post> patchPost(){
        Post post = postService.editPost();
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
}
