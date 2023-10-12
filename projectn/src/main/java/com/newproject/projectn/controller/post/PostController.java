package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.PostService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.Multi_ResponseDTO;
import com.newproject.projectn.dto.post.PostDtos;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
@AllArgsConstructor
public class PostController {

    PostService postService;
    UserService userService;
    PostMapper mapper;

    UriMaker uriMaker;

    @PostMapping("/create/v1")
    public ResponseEntity<String> postPost(@RequestBody PostDtos.PostPostDto postDto){
        User user = userService.findUser(postDto.getUserId());
        Post newPost = mapper.postPostDtoToPostEntity(postDto);
        newPost.setPostUser(user);
        Post post = postService.createPost(newPost);

        String redirectUri = uriMaker.uriMaker("post", ""+ post.getPostId()+"/v1");
        return new ResponseEntity<>(redirectUri, HttpStatus.OK);
    }
    @GetMapping("/{postId}/v1")
    public ResponseEntity<PostDtos.ResponseDtoForDetailPage> getPost(@PathVariable Long postId){
        Post post = postService.findPost(postId);
        PostDtos.ResponseDtoForDetailPage responseResult= mapper.PostEntityToResponseDtoForDetailPage(post);
        responseResult.setNickName(post.getPostUser().getNickName());
        responseResult.setUserId(post.getPostUser().getUserId());
        return new ResponseEntity<>(responseResult, HttpStatus.OK);

    }

    @GetMapping("/main/list/popular/v1")//커뮤니티의 인기글에 내보낼 리스트
    public ResponseEntity<List<PostDtos.MainResponseDto>> getMainPopularPostList(){
        List<Post> postList = postService.findPopularListForMain();
        List<PostDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> PostDtos.MainResponseDto.builder()
                        .postId(element.getPostId())
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post", "/" + element.getPostId() + "/v1"))
                        .author(element.getPostUser().getNickName())
                        .recommend(element.getRecommend())
                        .commentNum(element.getCommentList().size())
                        .view(element.getView())
                        .regTime(element.getRegTime())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/main/list/v1")// 커뮤니티의 오늘의 글에 올릴 리스트
    public ResponseEntity<List<PostDtos.MainResponseDto>> getMainPostList(){

        List<Post> postList = postService.findPostList(1, 4).stream().toList();
        List<PostDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> PostDtos.MainResponseDto.builder()
                        .postId(element.getPostId())
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post", "/" + element.getPostId()+ "/v1"))
                        .regTime(element.getRegTime())
                        .author(element.getPostUser().getNickName())
                        .recommend(element.getRecommend())
                        .commentNum(element.getCommentList().size())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")//오늘의 글 페이지리스트 보기
    public ResponseEntity<Multi_ResponseDTO> getPostList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        Page<Post> postList = postService.findPostList(pageIdx, postPerPage);
        List<PostDtos.ResponseDtoForList> resultDtoList =  postList.stream().map((element) -> PostDtos.ResponseDtoForList.builder()
                        .postId(element.getPostId())
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post", ""+ element.getPostId()+"/v1"))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .recommend(element.getRecommend())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Multi_ResponseDTO<>(resultDtoList, postList), HttpStatus.OK);
    }


    @GetMapping("/popular/list/{pageIdx}/v1")//오늘의 글 페이지리스트 보기
    public ResponseEntity<Multi_ResponseDTO> getPopularPostList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        Page<Post> postList = postService.findPopularList(pageIdx, postPerPage);
        List<PostDtos.ResponseDtoForList> resultDtoList =  postList.stream().map((element) -> PostDtos.ResponseDtoForList.builder()
                        .postId(element.getPostId())
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post", ""+ element.getPostId()+"/v1"))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .recommend(element.getRecommend())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Multi_ResponseDTO<>(resultDtoList, postList), HttpStatus.OK);
    }



    @GetMapping("/list/user/{userId}")//특정 유저가 작성한 글 목록 보기(내 글 보기 용)
    public ResponseEntity<List<Post>> getSpecificUserPost(@PathVariable Long userId, @RequestParam int pageIdx){

        List<Post> postList = postService.findSpecificUserPostList(userId, pageIdx);
        postList.stream().map(
                (element) -> PostDtos.ResponseDtoForList.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post", "/" + element.getPostId()))
                        .writerName(element.getPostUser().getNickName())
                        .commentNum(element.getCommentList().size())
                        .build());
        return new ResponseEntity<>(postList, HttpStatus.OK);

    }



    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchPost(@RequestBody PostDtos.PatchDto patchPostDto){
        User user = userService.findUser(patchPostDto.getUserId());
        Post editPost = mapper.patchPostDtoToPostEntity(patchPostDto);
        editPost.setPostUser(user);
        Post post = postService.editPost(editPost);

        String redirectUri = uriMaker.uriMaker("post", ""+ post.getPostId());
        return new ResponseEntity<>(redirectUri, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{postId}/v1")
    public void deletePost(@PathVariable int postId){
        postService.removePost(postId);

    }
}
