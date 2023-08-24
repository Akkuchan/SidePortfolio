package com.newproject.projectn.controller;

import com.newproject.projectn.Service.CommentService;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import com.newproject.projectn.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@AllArgsConstructor
public class CommentController {

    CommentService commentService;



    @PostMapping
    public ResponseEntity<Comment> postComment(@RequestBody Kindergarten kindergarten){


        Comment comment = commentService.createComment();
        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Comment> getComment(){

        Comment comment = commentService.findComment();

        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/list")
    public ResponseEntity< List<Comment>> getCommentList(){

        List<Comment> commentList = commentService.findCommentList();

        return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Comment> patchComment(){
        Comment comment = commentService.editComment();
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

}
