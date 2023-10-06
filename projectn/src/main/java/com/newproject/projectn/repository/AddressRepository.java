package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.address.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCity(City byCityName);

//    @Query("SELECT a\n" +
//            "FROM Address a \n" +
//            "JOIN Kindergarten k ON k.address.id = a.id\n" +
//            "JOIN a.city c\n" +
//            "WHERE c.id = :cityId " )
//    Page<Address> findAllByCity2(@Param("cityId") Long cityId,Pageable pageable);




}
