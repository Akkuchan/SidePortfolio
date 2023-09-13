package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.region.PostCityDto;
import com.newproject.projectn.dto.region.PostStateDto;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-13T15:59:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public State postStateDtoToStateEntity(PostStateDto postStateDto) {
        if ( postStateDto == null ) {
            return null;
        }

        State state = new State();

        state.setStateName( postStateDto.getStateName() );

        return state;
    }

    @Override
    public City postCityDtoToCityEntity(PostCityDto postCityDto) {
        if ( postCityDto == null ) {
            return null;
        }

        City city = new City();

        city.setCityName( postCityDto.getCityName() );

        return city;
    }
}
