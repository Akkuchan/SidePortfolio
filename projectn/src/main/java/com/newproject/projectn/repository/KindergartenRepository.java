package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, Long> {


    Optional<Kindergarten> findByName(String name);

    Optional<Kindergarten> existsByName(String kindergartenName);
}
