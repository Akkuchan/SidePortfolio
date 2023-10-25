package com.newproject.projectn.controller.post;


import com.newproject.projectn.Service.CommentService;
import com.newproject.projectn.Service.post.PostService;
import com.newproject.projectn.dto.recommend.RecommendDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("recommend")
@AllArgsConstructor
public class RecommendController {

    PostService postService;
    CommentService commentService;





    @PostMapping("/post/v1")
    public ResponseEntity plusRecommend(@RequestBody RecommendDto.plusPostRecommend recommendDto){
        postService.addRecommend(recommendDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/comment/v1")
    public ResponseEntity plusRecommend(@RequestBody RecommendDto.plusCommentRecommend recommendDto){
        commentService.addRecommend(recommendDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
