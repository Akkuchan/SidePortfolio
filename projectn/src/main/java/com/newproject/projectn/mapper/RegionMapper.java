package com.newproject.projectn.mapper;


import com.newproject.projectn.dto.region.PostCityDto;
import com.newproject.projectn.dto.region.PostStateDto;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface RegionMapper {

    State postStateDtoToStateEntity(PostStateDto postStateDto);
    City postCityDtoToCityEntity(PostCityDto postCityDto);
}
