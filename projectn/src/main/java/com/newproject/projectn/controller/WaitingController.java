package com.newproject.projectn.controller;

import com.newproject.projectn.Service.WaitingService;
import com.newproject.projectn.entitiy.Waiting;
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


    @PostMapping
    public ResponseEntity<Waiting> postWaiting(@RequestBody Waiting waiting){

        Waiting newWaiting = waitingService.createWaiting();
        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Waiting> getWaiting(){

        Waiting waiting = waitingService.findWaiting();

        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Waiting>> getWaitingList(){

        List<Waiting> waitingList = waitingService.findWaitingList();

        return new ResponseEntity<List<Waiting>>(waitingList, HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Waiting> patchWaiting(){
        Waiting waiting = waitingService.editWaiting();
        return new ResponseEntity<Waiting>(waiting, HttpStatus.OK);

    }





}
