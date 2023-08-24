package com.newproject.projectn.controller;

import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    CommentService commentService;


    @PostMapping
    public ResponseEntity<Comment> postKindergarten(@RequestBody Kindergarten){

        Post post = commentService.createComment();
        return new ResponseEntity<Comment>();
    }

    @GetMapping
    public ResponseEntity<Comment> getKindergarten(){

        Comment post = commentService.findComment();

        return new ResponseEntity<Comment>();
    }

    @GetMapping
    public ResponseEntity<Comment> getKindergartenList(){

        List<Post> Post = commentService.findComment();

        return new ResponseEntity<Comment>();
    }

    @PatchMapping
    public ResponseEntity<Comment> patchKindergarten(){
        Comment comment = commentService.editComment();
        return new ResponseEntity<Comment>();
    }

}
