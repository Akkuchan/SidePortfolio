package com.newproject.projectn.controller.post;

import com.newproject.projectn.Service.post.ParentingEventService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.post.ParentingEventDtos;
import com.newproject.projectn.entitiy.post.ParentingEvent;
import com.newproject.projectn.mapper.PostMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("event")
@AllArgsConstructor
public class ParentingEventController {


    ParentingEventService parentingEventService;
    PostMapper mapper;
    UriMaker uriMaker;
    @PostMapping("/create/v1")
    public ResponseEntity<String> postParentingEvent(@RequestBody ParentingEventDtos.PostDto postDto) {
        ParentingEvent newLocalInfo = mapper.postParentingEventDtoToParentingEventEntity(postDto);
        ParentingEvent createdParentingEvent = parentingEventService.createParentingEvent(newLocalInfo, postDto.getUserId());
        String redirectUri = uriMaker.uriMaker("post/event", ""+ createdParentingEvent.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);
    }

    @GetMapping("/{parentingEventId}/v1")
    public ResponseEntity<ParentingEventDtos.ResponseDtoForDetailPage> getParentingEvent(@PathVariable Long parentingEventId) {
        ParentingEvent foundParentingEvent = parentingEventService.findParentingEvent(parentingEventId);
        ParentingEventDtos.ResponseDtoForDetailPage detailedResponsedto = mapper.ParentingEventEntityToResponseDtoForDetailPageDto(foundParentingEvent);
        detailedResponsedto.setUserId(foundParentingEvent.getPostUser().getUserId());
        detailedResponsedto.setNickName(foundParentingEvent.getPostUser().getNickName());

        return new ResponseEntity<ParentingEventDtos.ResponseDtoForDetailPage>(detailedResponsedto, HttpStatus.OK);
    }

    @GetMapping("/main/list/v1")//커뮤니티 메인 페이지에 내보낼 리스트
    public ResponseEntity<List<ParentingEventDtos.MainResponseDto>> getMainParentingEventList(){
        List<ParentingEvent> postList = parentingEventService.findParentingEventList(0,4);
        List<ParentingEventDtos.MainResponseDto> resultList = postList.stream()
                .map((element) -> ParentingEventDtos.MainResponseDto.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post/event", "/" + element.getPostId()))
                        .regTime(element.getRegTime())
                        .build()).toList();

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")//커뮤니티 메인 페이지에 내보낼 리스트
    public ResponseEntity<List<ParentingEventDtos.ResponseDtoForList>> getParentingEventList(@RequestParam int postPerPage , @PathVariable int pageIdx){
        List<ParentingEvent> postList = parentingEventService.findParentingEventList(postPerPage,pageIdx);
        List<ParentingEventDtos.ResponseDtoForList> resultList = postList.stream()
                .map((element) -> ParentingEventDtos.ResponseDtoForList.builder()
                        .title(element.getTitle())
                        .url(uriMaker.uriMaker("post/event", ""+ element.getPostId()))
                        .commentNum(element.getCommentList().size())
                        .writerName(element.getPostUser().getNickName())
                        .views(element.getView())
                        .regTime(element.getRegTime())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @PatchMapping("/edit/v1")
    public ResponseEntity<String> patchParentingEvent(@RequestBody ParentingEventDtos.PatchDto patchDto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ParentingEvent editParentingEvent = mapper.patchParentingEventDtoToParentingEventEntity(patchDto);
        ParentingEvent editedParentingEvent = parentingEventService.editParentingEvent(editParentingEvent);

        String redirectUri = uriMaker.uriMaker("post/event", ""+ editedParentingEvent.getPostId());

        return new ResponseEntity<String>(redirectUri, HttpStatus.OK);

    }

    @DeleteMapping("/{parentingEventId}")
    public void deleteParentingEvent(@PathVariable Long parentingEventId) {
        parentingEventService.deleteParentingEvent(parentingEventId);
    }



}
