package com.newproject.projectn.controller;

import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Post;
import com.newproject.projectn.entitiy.Waiting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WaitingListController {

    WaitingService waitingService;


    @PostMapping
    public ResponseEntity<Waiting> postKindergarten(@RequestBody Waiting){

        Waiting waiting = waitingService.createWaiting();
        return new ResponseEntity<Waiting>();
    }

    @GetMapping
    public ResponseEntity<Waiting> getKindergarten(){

        Waiting waiting = waitingService.findWaiting();

        return new ResponseEntity<Waiting>();
    }

    @GetMapping
    public ResponseEntity<Waiting> getKindergartenList(){

        List<Waiting> waiting = waitingService.findWaiting();

        return new ResponseEntity<Waiting>();
    }

    @PatchMapping
    public ResponseEntity<Waiting> patchKindergarten(){
        Waiting comment = waitingService.editWaiting();
        return new ResponseEntity<Waiting>();
    }





}
