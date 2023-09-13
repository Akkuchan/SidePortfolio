package com.newproject.projectn.controller;

import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.mapper.KindergartenMapper;
import com.newproject.projectn.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("kindergarten")
@AllArgsConstructor

public class KindergartenController {

    KindergartenService kindergartenService;
    KindergartenMapper mapper;
    AddressRepository addressRepository;

    @PostMapping
    public ResponseEntity<Kindergarten> postKindergarten(@RequestBody PostKindergartenDto postKindergartenDto){

        Kindergarten kindergarten = mapper.postKindergartenDtoToKindergartenEntity(postKindergartenDto);
        Kindergarten newKindergarten = kindergartenService.createKindergarten(kindergarten, postKindergartenDto.getCityId(),postKindergartenDto.getDetails(), postKindergartenDto.getZipcode() );
        return new ResponseEntity<Kindergarten>(newKindergarten,HttpStatus.OK);

    }

    @GetMapping("/{kindergartenId}")
    public ResponseEntity<Kindergarten> getKindergarten(@PathVariable Long kindergartenId){

        Kindergarten kindergarten = kindergartenService.findKindergarten(kindergartenId);

        return new ResponseEntity<Kindergarten>(kindergarten, HttpStatus.OK);
    }

    @GetMapping("/list/v1/{pageIdx}")
    public ResponseEntity<List<Kindergarten>> getKindergartenListV1(@PathVariable Integer pageIdx){

        List<Kindergarten> kindergartenList = kindergartenService.findKindergartenList(pageIdx-1);



        return new ResponseEntity<List<Kindergarten>>(kindergartenList, HttpStatus.OK);

    }

    @GetMapping("/list/v2/{pageIdx}")
    public ResponseEntity<List<Kindergarten>> getKindergartenListV2(@RequestParam String state, @RequestParam String city, @RequestParam @Nullable String kindergartenName, @PathVariable Integer pageIdx){




        List<Kindergarten> kindergartenList = kindergartenService.findKindergartenListByStateAndCity(state, city, kindergartenName, pageIdx-1);



        return new ResponseEntity<List<Kindergarten>>(kindergartenList,HttpStatus.OK);

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
