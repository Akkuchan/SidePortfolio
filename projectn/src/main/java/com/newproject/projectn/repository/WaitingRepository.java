package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRepository extends JpaRepository<Waiting, Long> {



}
