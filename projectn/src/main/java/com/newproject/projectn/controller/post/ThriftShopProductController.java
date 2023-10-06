package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.ThriftShopProductService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.post.ThriftShopProductDtos;

import com.newproject.projectn.entitiy.post.ThriftShopProduct;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/thriftshop")
@AllArgsConstructor
public class ThriftShopProductController {

    ThriftShopProductService thriftShopProductService;
    PostMapper mapper;
    UriMaker uriMaker;
    @PostMapping("/create/v1)")
    public ResponseEntity<String> postThriftShopProduct(@RequestBody ThriftShopProductDtos.PostDto postDto) {
        ThriftShopProduct newLocalInfo = mapper.postThriftShopProductDtoToThriftShopProductEntity(postDto);
        ThriftShopProduct createdThriftShopProduct = thriftShopProductService.createThriftShopProduct(newLocalInfo, postDto.getUserId());
        String redirectUri = uriMaker.uriMaker("thriftshop", ""+ createdThriftShopProduct.getPostId());
        return new ResponseEntity<>(redirectUri, HttpStatus.OK);
    }

    @GetMapping("/{thriftShopProductId}/v1")
    public ResponseEntity<ThriftShopProductDtos.ResponseDtoForDetailPage> getThriftShopProduct(@PathVariable Long thriftShopProductId) {
        ThriftShopProduct foundedThriftShopProduct = thriftShopProductService.findThriftShopProduct(thriftShopProductId);

        ThriftShopProductDtos.ResponseDtoForDetailPage detailedResponseDto = mapper.thriftShopProductEntityToResponseDtoForDetailPageDto(foundedThriftShopProduct);
        detailedResponseDto.setUserId(foundedThriftShopProduct.getPostUser().getUserId());
        detailedResponseDto.setNickName(foundedThriftShopProduct.getPostUser().getNickName());

        return new ResponseEntity<>(detailedResponseDto, HttpStatus.OK);

    }

    @GetMapping("/main/list/v1")// 커뮤니티의 오늘의 글에 올릴 리스트
    public ResponseEntity<List<ThriftShopProductDtos.MainResponseDto>> getMainThriftShopProductList(){

        List<ThriftShopProduct> postList = thriftShopProductService.findThriftShopProductList(0, 4);
        List<ThriftShopProductDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> ThriftShopProductDtos.MainResponseDto.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("thriftshop", "" + element.getPostId()))
                        .regTime(element.getRegTime())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")//오늘의 글 페이지리스트 보기
    public ResponseEntity< List<ThriftShopProductDtos.ResponseDtoForList>> getPostList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        List<ThriftShopProduct> postList = thriftShopProductService.findThriftShopProductList(pageIdx, postPerPage);
        List<ThriftShopProductDtos.ResponseDtoForList> resultDtoList =  postList.stream().map((element) -> ThriftShopProductDtos.ResponseDtoForList.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("thriftshop", ""+ element.getPostId()))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .views(element.getView())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }


    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchParentingProduct(@RequestBody ThriftShopProductDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ThriftShopProduct editThriftShopProduct = mapper.patchThriftShopProductDtoToThriftShopProductEntity(patchDto);
        ThriftShopProduct editedThriftShopProduct = thriftShopProductService.editThriftShopProduct(editThriftShopProduct);

        String redirectUri = uriMaker.uriMaker("post/thriftshop", ""+ editedThriftShopProduct.getPostId());

        return new ResponseEntity<>(redirectUri, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{thriftshopproductid}/v1")
    public void deleteParentingProduct(@PathVariable Long thriftShopProductId) {
        thriftShopProductService.deleteThriftShopProduct(thriftShopProductId);
    }



}
