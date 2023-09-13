package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository  extends JpaRepository<State, Long> {
    Optional<State> findByStateName(String region);
}
