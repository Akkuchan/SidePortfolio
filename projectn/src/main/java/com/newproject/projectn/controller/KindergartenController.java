package com.newproject.projectn.controller;

import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.mapper.KindergartenMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("kindergarten")
@AllArgsConstructor

public class KindergartenController {

    KindergartenService kindergartenService;
    KindergartenMapper mapper;
    @PostMapping
    public ResponseEntity<Kindergarten> postKindergarten(@RequestBody PostKindergartenDto postKindergartenDto){
        Kindergarten kindergarten = mapper.postKindergartenDtoToKindergartenEntity(postKindergartenDto);
        kindergarten.setStartTime(LocalTime.of(9,0));
        kindergarten.setEndTime(LocalTime.of(18,0));
        Kindergarten newKindergarten = kindergartenService.createKindergarten(kindergarten);
        return new ResponseEntity<Kindergarten>(newKindergarten,HttpStatus.OK);

    }

    @GetMapping("/{kindergartenId}")
    public ResponseEntity<Kindergarten> getKindergarten(@PathVariable Long kindergartenId){

        Kindergarten kindergarten = kindergartenService.findKindergarten(kindergartenId);

        return new ResponseEntity<Kindergarten>(kindergarten, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}")
    public ResponseEntity<Kindergarten> getKindergartenList(@PathVariable Integer pageIdx){

        List<Kindergarten> kindergartenList = kindergartenService.findKindergartenList(pageIdx);



        return new ResponseEntity<Kindergarten>(HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Kindergarten> patchKindergarten(@RequestBody PatchKindergartenDto patchKindergartenDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Kindergarten kindergarten  =mapper.patchKindergartenDtoToKindergartenEntity(patchKindergartenDto);
        Kindergarten editedKindergarten = kindergartenService.editKindergarten(kindergarten);
        return new ResponseEntity<Kindergarten>(editedKindergarten, HttpStatus.OK);

    }

    @DeleteMapping("/{kindergartenId}")
    public ResponseEntity<Kindergarten> deleteKindergarten(@PathVariable Long kindergartenId){

        kindergartenService.removeKindergarten(kindergartenId);

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);
    }

}
