package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.LocalInfoService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.post.LocalInfoDtos;
import com.newproject.projectn.dto.post.PostDtos;
import com.newproject.projectn.entitiy.post.LocalInfo;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("localinfo")
@AllArgsConstructor
public class LocalInfoController {


    LocalInfoService localInfoService;
    PostMapper mapper;
    UriMaker uriMaker;

    @PostMapping("/create/v1")
    public ResponseEntity<String> postLocalInfo(@RequestBody LocalInfoDtos.PostDto postDto) {
        LocalInfo newLocalInfo = mapper.postLocalInfoDtoToLocalInfoEntity(postDto);
        LocalInfo createdFaq = localInfoService.createLocalInfo(newLocalInfo, postDto.getUserId(), postDto.getCityId());
        String redirectUri = uriMaker.uriMaker("localinfo", ""+ createdFaq.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);
    }

    @GetMapping("/{localInfoId}/v1")
    public ResponseEntity<LocalInfoDtos.ResponseDtoForDetailPage> getLocalInfo(@PathVariable Long localInfoId) {
        LocalInfo foundFAQ = localInfoService.findLocalInfo(localInfoId);
        LocalInfoDtos.ResponseDtoForDetailPage detailedResponseDto = mapper.LocalInfoEntityToResponseDtoForDetailPage(foundFAQ);
        detailedResponseDto.setUserId(foundFAQ.getPostUser().getUserId());
        detailedResponseDto.setNickName(foundFAQ.getPostUser().getNickName());

        return new ResponseEntity<LocalInfoDtos.ResponseDtoForDetailPage>(detailedResponseDto, HttpStatus.OK);

    }

    @GetMapping("/main/list/v1")// 커뮤니티 메인에 올릴 리스트
    public ResponseEntity<List<LocalInfoDtos.MainResponseDto>> getMainLocalInfoList(){

        List<LocalInfo> postList =localInfoService.findLocalInfoList(0, 4);
        List<LocalInfoDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> LocalInfoDtos.MainResponseDto.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("localinfo", "/" + element.getPostId()))
                        .regTime(element.getRegTime())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}")// 입소문 게시판 글 리스트 가져오기
    public ResponseEntity<List<LocalInfoDtos.ResponseDtoForList>> getLocalInfoList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        List<LocalInfo> localInfoList = localInfoService.findLocalInfoList(pageIdx, postPerPage);
        List<LocalInfoDtos.ResponseDtoForList> resultDtoList =  localInfoList.stream().map((element) -> LocalInfoDtos.ResponseDtoForList.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("localinfo", ""+ element.getPostId()))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .views(element.getView())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }


    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchLocalInfo(@RequestBody LocalInfoDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        LocalInfo editFaq = mapper.patchLocalInfoDtoToLocalInfoEntity(patchDto);
        LocalInfo editedFaq = localInfoService.editLocalInfo(editFaq);

        String redirectUri = uriMaker.uriMaker("localinfo", ""+ editedFaq.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{localinfoid}/v1")
    public void deleteLocalInfo(@PathVariable Long localInfoId) {
        localInfoService.deleteLocalInfo(localInfoId);
    }
}
