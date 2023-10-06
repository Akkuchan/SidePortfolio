package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.ParentingProductService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.post.ParentingProductDtos;
import com.newproject.projectn.entitiy.post.ParentingProduct;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ParentingProdController {


    ParentingProductService parentingProductService;
    PostMapper mapper;
    UriMaker uriMaker;
    @PostMapping("/create/v1")
    public ResponseEntity<String> postParentingProduct(@RequestBody ParentingProductDtos.PostDto postDto) {
        ParentingProduct newLocalInfo = mapper.postParentingProductDtoToParentingProductEntity(postDto);
        ParentingProduct createdParentingEvent = parentingProductService.createParentingProduct(newLocalInfo, postDto.getUserId());
        String redirectUri = uriMaker.uriMaker("post/product", ""+ createdParentingEvent.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);
    }

    @GetMapping("/{parentingProductId}")
    public ResponseEntity<ParentingProductDtos.ResponseDtoForDetailPage> getParentingProduct(@PathVariable Long parentingProductId) {
        ParentingProduct foundParentingEvent = parentingProductService.findParentingProduct(parentingProductId);

        ParentingProductDtos.ResponseDtoForDetailPage detailedResponseDto  = mapper.ParentingProductEntityToResponseDtoForDetailPageSto(foundParentingEvent);
        detailedResponseDto.setUserId(foundParentingEvent.getPostUser().getUserId());
        detailedResponseDto.setNickName(foundParentingEvent.getPostUser().getNickName());

        return new ResponseEntity<ParentingProductDtos.ResponseDtoForDetailPage>(detailedResponseDto, HttpStatus.OK);

    }

    @GetMapping("/main/list/v1")// 커뮤니티의 오늘의 글에 올릴 리스트
    public ResponseEntity<List<ParentingProductDtos.MainResponseDto>> getMainParentingProductList(){

        List<ParentingProduct> postList = parentingProductService.findPostList(0, 4);
        List<ParentingProductDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> ParentingProductDtos.MainResponseDto.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post/product", "/" + element.getPostId()))
                        .regTime(element.getRegTime())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")//오늘의 글 페이지리스트 보기
    public ResponseEntity< List<ParentingProductDtos.ResponseDtoForList>> getParentingProductList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        List<ParentingProduct> postList = parentingProductService.findPostList(pageIdx, postPerPage);
        List<ParentingProductDtos.ResponseDtoForList> resultDtoList =  postList.stream().map((element) -> ParentingProductDtos.ResponseDtoForList.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post/product", ""+ element.getPostId()))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .views(element.getView())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }



    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchParentingProduct(@RequestBody ParentingProductDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ParentingProduct editParentingEvent = mapper.patchParentingProductDtoToParentingProductEntity(patchDto);
        ParentingProduct editedParentingEvent = parentingProductService.editParentingProduct(editParentingEvent);
        String redirectUri = uriMaker.uriMaker("post/product", ""+ editedParentingEvent.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);

    }

    @DeleteMapping("/{parentingProductId}")
    public void deleteParentingProduct(@PathVariable Long parentingProductId) {
        parentingProductService.deleteParentingProduct(parentingProductId);
    }



}
