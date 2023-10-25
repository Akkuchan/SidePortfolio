package com.newproject.projectn.controller;

import com.newproject.projectn.Service.EnrollService;
import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.Service.WaitingService;

import com.newproject.projectn.dto.waiting.WaitingDto;
import com.newproject.projectn.entitiy.Enroll;
import com.newproject.projectn.entitiy.Waiting;
import com.newproject.projectn.mapper.WaitingMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("waiting")
@AllArgsConstructor

public class WaitingController {

    WaitingService waitingService;
    WaitingMapper mapper;
    UserService userService;
    KindergartenService kindergartenService;
    EnrollService enrollService;

    @PostMapping
    public ResponseEntity<Waiting> postWaiting(@RequestBody WaitingDto.PostDto postWaitingDto){



        Waiting newWaiting = mapper.postWaitingDtoToWaitingEntity(postWaitingDto);
        newWaiting.setUser(userService.findUser(postWaitingDto.getUserId()));
        newWaiting.setEnroll(enrollService.findEnroll(postWaitingDto.getEnrollId()));

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

    @GetMapping("/pending")
    public ResponseEntity<List<Waiting>> getPendingListForMain(){

        List<Waiting> waitingList = waitingService.findPedingWaitingList();

        return new ResponseEntity<List<Waiting>>(waitingList, HttpStatus.OK);

    }


    @PatchMapping
    public ResponseEntity<Waiting> patchWaiting(){
        Waiting waiting = waitingService.editWaiting();
        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);

    }



}
