package com.newproject.projectn.controller;

import com.newproject.projectn.Service.CommentService;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    CommentService commentService;


    @PostMapping
    public ResponseEntity<Comment> postKindergarten(@RequestBody Kindergarten kindergarten){

        Comment comment = commentService.createComment();
        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Comment> getKindergarten(){

        Comment comment = commentService.findComment();

        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));

    }

    @GetMapping
    public ResponseEntity< List<Comment>> getKindergartenList(){

        List<Comment> commentList = commentService.findCommentList();

        return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Comment> patchKindergarten(){
        Comment comment = commentService.editComment();
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

}
