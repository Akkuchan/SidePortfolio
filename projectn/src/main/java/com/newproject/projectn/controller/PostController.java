package com.newproject.projectn.controller;

import com.newproject.projectn.Service.PostService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.dto.PatchPostDto;
import com.newproject.projectn.dto.PostPostDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.mapper.PostMapper;
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
    UserService userService;
    PostMapper mapper;

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody PostPostDto postDto){
        User user = userService.findUser(postDto.getUserId());
        Post newPost = mapper.postPostDtoToPostEntity(postDto);
        newPost.setPostUser(user);
        Post post = postService.createPost(newPost);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/{postIdx}")
    public ResponseEntity<Post> getPost(@PathVariable Long postIdx){

        Post post = postService.findPost(postIdx);

        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @GetMapping("/list/{pageIdx}")
    public ResponseEntity<List<Post>> getPostList(@PathVariable int pageIdx){

        List<Post> postList = postService.findPostList(pageIdx);

        return new ResponseEntity<>(postList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Post> patchPost(@RequestBody PatchPostDto patchPostDto){
        User user = userService.findUser(patchPostDto.getUserId());
        Post editPost = mapper.patchPostDtoToPostEntity(patchPostDto);
        editPost.setPostUser(user);
        Post post = postService.editPost(editPost);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
}
