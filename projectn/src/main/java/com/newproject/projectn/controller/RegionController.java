package com.newproject.projectn.controller;

import com.newproject.projectn.Service.RegionService;
import com.newproject.projectn.dto.region.PostCityDto;
import com.newproject.projectn.dto.region.PostStateDto;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import com.newproject.projectn.mapper.RegionMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("region")
@AllArgsConstructor
public class RegionController {

    RegionService regionService;
    RegionMapper regionMapper;


    @PostMapping("/state/v1")
    public ResponseEntity<State> postState(@RequestBody PostStateDto postStateDto){
        State newState = regionMapper.postStateDtoToStateEntity(postStateDto);

        State addedState = regionService.addNewState(newState);

        return new ResponseEntity<>(addedState, HttpStatus.OK);

    }

    @PostMapping("/city/v1")
    public ResponseEntity<City> postCity(@RequestBody PostCityDto postCityDto){
        City newCity = regionMapper.postCityDtoToCityEntity(postCityDto);
        City addedCity = regionService.addNewCity(postCityDto.getStateName(), newCity);

        return new ResponseEntity<>(addedCity, HttpStatus.OK);

    }


    @GetMapping("/list/v1")
    public ResponseEntity<List<City>> getCityList(@RequestParam String region){

        List<City> cityList = regionService.findCityList(region);

        return new ResponseEntity<>(cityList, HttpStatus.OK);

    }


}
