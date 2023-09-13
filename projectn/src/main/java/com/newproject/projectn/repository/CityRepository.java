package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long > {


    Optional<City> findByCityName(String region);


    List<City> findByState(State state);

    Optional<City> findByStateAndCityName( State state, String city);
}
