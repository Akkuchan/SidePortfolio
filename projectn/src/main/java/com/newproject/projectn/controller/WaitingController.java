package com.newproject.projectn.controller;

import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.Service.WaitingService;
import com.newproject.projectn.dto.waiting.PostWaitingDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Waiting;
import com.newproject.projectn.mapper.WaitingMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waiting")
@AllArgsConstructor

public class WaitingController {

    WaitingService waitingService;
    WaitingMapper mapper;
    UserService userService;
    KindergartenService kindergartenService;

    @PostMapping
    public ResponseEntity<Waiting> postWaiting(@RequestBody PostWaitingDto postWaitingDto){

        Waiting newWaiting = mapper.postWaitingDtoToWaitingEntity(postWaitingDto);
        newWaiting.setUser(userService.findUser(postWaitingDto.getUserId()));
        newWaiting.setKindergarten(kindergartenService.findKindergartenById(postWaitingDto.getKindergartenId()));

        Waiting createdWaiting = waitingService.createWaiting(newWaiting);
        return new ResponseEntity<Waiting>(createdWaiting, HttpStatus.OK);
    }

    @GetMapping("/{waitingId}")
    public ResponseEntity<Waiting> getWaiting(@PathVariable Long waitingId){

        Waiting waiting = waitingService.findWaiting(waitingId);

        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);

    }

    @GetMapping("/list/{pageIdx}")
    public ResponseEntity<List<Waiting>> getWaitingList(@PathVariable int pageIdx){

        List<Waiting> waitingList = waitingService.findWaitingList(pageIdx);

        return new ResponseEntity<List<Waiting>>(waitingList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Waiting> patchWaiting(){
        Waiting waiting = waitingService.editWaiting();
        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);

    }





}
