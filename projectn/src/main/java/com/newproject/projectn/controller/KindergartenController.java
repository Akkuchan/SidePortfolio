package com.newproject.projectn.controller;

import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.entitiy.Kindergarten;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("kindergarten")
public class KindergartenController {

    KindergartenService kindergartenService;

    @PostMapping
    public ResponseEntity<Kindergarten> postKindergarten(@RequestBody Kindergarten kindergarten){

        Kindergarten newKindergarten = kindergartenService.createKindergarten();

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<Kindergarten> getKindergarten(){

        Kindergarten kindergarten = kindergartenService.findKindergarten();

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Kindergarten> getKindergartenList(){

        List<Kindergarten> kindergartenList = kindergartenService.findKindergartenList();

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Kindergarten> patchKindergarten(){
        Kindergarten kindergarten = kindergartenService.editKindergarten();
        return new ResponseEntity<Kindergarten>(HttpStatus.OK);

    }



}
