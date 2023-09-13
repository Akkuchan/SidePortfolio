package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCity(City byCityName);
}
