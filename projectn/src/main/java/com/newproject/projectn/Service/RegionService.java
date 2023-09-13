package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import com.newproject.projectn.repository.CityRepository;
import com.newproject.projectn.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {

    StateRepository stateRepository;
    CityRepository cityRepository;

    public State addNewState(State state){
        return stateRepository.save(state);
    }


    public City addNewCity(String stateName, City city ){
        State state = stateRepository.findByStateName(stateName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        city.setState(state);

        return cityRepository.save(city);
    }




    public List<City> findCityList(String region) {
       State state = stateRepository.findByStateName(region).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
       List<City> cities = cityRepository.findByState(state);

       return cities;
    }


}
