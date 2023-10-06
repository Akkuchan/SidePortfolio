package com.newproject.projectn.controller;

import com.newproject.projectn.Service.CommentService;
import com.newproject.projectn.Service.post.PostService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.dto.comment.PatchCommentDto;
import com.newproject.projectn.dto.comment.PostCommentDto;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("comment")
@AllArgsConstructor
public class CommentController {

    UserService userService;
    PostService postService;
    CommentService commentService;
    CommentMapper mapper;


    @PostMapping
    public ResponseEntity<Comment> postComment(@RequestBody PostCommentDto postCommentDto){

        Comment newComment = mapper.patchCommentDtoToCommentEntity(postCommentDto);
        newComment.setCommentingUser(userService.findUser(postCommentDto.getUserId()));
        newComment.setCommentedPost(postService.findPost(postCommentDto.getPostId()));
        Comment comment = commentService.createComment(newComment);
        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId){

        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<Comment>(comment, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/list/{pageIdx}")
    public ResponseEntity< List<Comment>> getCommentList(@PathVariable int pageIdx){

        List<Comment> commentList = commentService.findCommentList(pageIdx);

        return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Comment> patchComment(@RequestBody PatchCommentDto patchCommentDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Comment editComment = mapper.patchCommentDtoToCommentEntity(patchCommentDto);
        editComment.setCommentingUser(userService.findUser(patchCommentDto.getUserId()));
        editComment.setCommentedPost(postService.findPost(patchCommentDto.getPostId()));

        Comment comment = commentService.editComment(editComment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

}
