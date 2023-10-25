package com.newproject.projectn.controller;

import com.newproject.projectn.Service.KindergartenService;
import com.newproject.projectn.config.UriMaker;
import com.newproject.projectn.dto.Multi_ResponseDTO;
import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.dto.kindergarten.ResponseKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.mapper.KindergartenMapper;
import com.newproject.projectn.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("kindergarten")
@AllArgsConstructor

public class KindergartenController {

    KindergartenService kindergartenService;
    KindergartenMapper mapper;
    AddressRepository addressRepository;
    UriMaker uriMaker;

    @PostMapping("/create/v1")
    public ResponseEntity<Kindergarten> postKindergarten(@RequestBody PostKindergartenDto postKindergartenDto){

        Kindergarten kindergarten = KindergartenMapper.postKindergartenDtoToKindergartenEntity(postKindergartenDto);
        Kindergarten newKindergarten = kindergartenService.createKindergarten(kindergarten, postKindergartenDto.getCityId(),postKindergartenDto.getDetails(), postKindergartenDto.getZipcode() );
        return new ResponseEntity<Kindergarten>(newKindergarten,HttpStatus.OK);

    }

    @GetMapping("/{kindergartenId}")
    public ResponseEntity<Kindergarten> getKindergarten(@PathVariable Long kindergartenId){

        Kindergarten kindergarten = kindergartenService.findKindergarten(kindergartenId);

        return new ResponseEntity<Kindergarten>(kindergarten, HttpStatus.OK);
    }

    @GetMapping("/list/{pageIdx}/v1")
    public ResponseEntity<Multi_ResponseDTO> getKindergartenListV1(@PathVariable Integer pageIdx){

        Page<Kindergarten> kindergartenList = kindergartenService.findKindergartenList(pageIdx-1);

        Multi_ResponseDTO responseDTO =  new Multi_ResponseDTO<>(kindergartenList.getContent(), kindergartenList);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/list/{pageIdx}/v2")
    public ResponseEntity<Multi_ResponseDTO> getKindergartenListV2(@RequestParam String state, @RequestParam String city, @RequestParam @Nullable String kindergartenName, @RequestParam int elementPerPage, @PathVariable Integer pageIdx){

        Page<Kindergarten> kindergartenPage = kindergartenService.findKindergartenListByStateAndCity(state, city, kindergartenName, pageIdx-1, elementPerPage);
        List<ResponseKindergartenDto> responseDtoList = kindergartenPage.stream().toList().stream().map(KindergartenMapper::KindergartenEntityToResponseKindergartenDto).collect(Collectors.toList());
        responseDtoList.stream().peek((e) -> e.setUrl(uriMaker.uriMaker("kindergarten", ""+ e.getKindergartenId()))).collect(Collectors.toList());

        return new ResponseEntity<>( new Multi_ResponseDTO( responseDtoList, kindergartenPage),HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<Kindergarten> patchKindergarten(@RequestBody PatchKindergartenDto patchKindergartenDto) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Kindergarten kindergarten  =mapper.patchKindergartenDtoToKindergartenEntity(patchKindergartenDto);
        Kindergarten editedKindergarten = kindergartenService.editKindergarten(kindergarten);

        return new ResponseEntity<Kindergarten>(editedKindergarten, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{kindergartenId}/v1")
    public ResponseEntity<Kindergarten> deleteKindergarten(@PathVariable Long kindergartenId){

        kindergartenService.removeKindergarten(kindergartenId);

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);
    }

}
