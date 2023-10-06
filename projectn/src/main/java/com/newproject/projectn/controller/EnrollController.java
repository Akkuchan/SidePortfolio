package com.newproject.projectn.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.newproject.projectn.Service.EnrollService;
import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.dto.Multi_ResponseDTO;
import com.newproject.projectn.dto.enroll.EnrollDtos;
import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Enroll;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.mapper.EnrollMapper;
import com.newproject.projectn.mapper.KindergartenMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("enroll")
@AllArgsConstructor
public class EnrollController {

    EnrollService enrollService;
    KindergartenService kindergartenService;
    EnrollMapper enrollMapper;


    @PostMapping("/create/v1")
    public ResponseEntity<Enroll> postKindergarten(@RequestBody EnrollDtos.PostEnrollDto postEnrollDto){

        Enroll newEnroll = enrollMapper.postEnrollDtoToEnrollEntity(postEnrollDto);
        newEnroll.setKindergarten(kindergartenService.findKindergarten(postEnrollDto.getKindergartenId()));
        Enroll addedEnroll = enrollService.createEnroll(newEnroll);
        return new ResponseEntity<Enroll>(addedEnroll,HttpStatus.OK);
    }

   @GetMapping("/main")
    public ResponseEntity getEnrollListForMain(){//마감까지 얼마 남지 않은 최신 리스트 3가지 호출, 유치원 명 + 마감일 + 마감시간
        List<Enroll> enrollList = enrollService.findEnding3Enrollments();
       List<EnrollDtos.ResultForMain> resultDtos= enrollList.stream().map(EnrollMapper::enrollEntityToResultDtos).collect(Collectors.toList());

        return new ResponseEntity<>(resultDtos, HttpStatus.OK);
   }

    @GetMapping("/list/v1")
    public ResponseEntity<Multi_ResponseDTO> getEnrollList(@RequestParam int pageIdx, @RequestParam int pageSize ){
        Page<Enroll> enrollList = enrollService.findEnrollList(pageIdx, pageSize);
        List<EnrollDtos.ResultForList> resultDtos= enrollList.getContent().stream().map(EnrollMapper::enrollEntityToResultListDtos).collect(Collectors.toList());

        return new ResponseEntity<>(new Multi_ResponseDTO<>(resultDtos, enrollList), HttpStatus.OK);
    }

    @PatchMapping("/edit/starttime")
    public ResponseEntity<Enroll> patchEnrollStarttime(@RequestBody EnrollDtos.PatchEnrollStartTimeDto patchEnrollStartTimeDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Enroll editedEnroll = enrollService.editStartTime(patchEnrollStartTimeDto.getEnrollId(), patchEnrollStartTimeDto.getEnrollStartTime());
        return new ResponseEntity<>(editedEnroll, HttpStatus.OK);

    }

    @PatchMapping("/edit/endttime")
    public ResponseEntity<Enroll> patchEnrollStarttime(@RequestBody EnrollDtos.PatchEnrollEndTimeDto patchEnrollStartTimeDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Enroll editedEnroll = enrollService.editEndTime(patchEnrollStartTimeDto.getEnrollId(), patchEnrollStartTimeDto.getEnrollEndTime());
        return new ResponseEntity<>(editedEnroll, HttpStatus.OK);

    }

}
