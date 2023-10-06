package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Waiting;
import com.newproject.projectn.entitiy.address.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.*;
@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {


    Optional<Kindergarten> findByName(String name);

    Optional<Kindergarten> existsByName(String kindergartenName);

    Arrays findByAddress(Address address, PageRequest updateTime);

//    Page<Kindergarten> findAll(Address address, PageRequest updateTime);

    Optional<Kindergarten> findAllByAddress(Address address);


    @Query("SELECT k\n" +
            "FROM Kindergarten k \n" +
            "JOIN Address a ON k.address.id = a.id\n" +
            "JOIN a.city c\n" +
            "WHERE c.id = :cityId " )
    Page<Kindergarten> findAllByCity3(@Param("cityId") Long cityId, Pageable pageable);


}
