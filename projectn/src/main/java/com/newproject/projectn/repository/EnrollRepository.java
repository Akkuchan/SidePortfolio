package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {
}
