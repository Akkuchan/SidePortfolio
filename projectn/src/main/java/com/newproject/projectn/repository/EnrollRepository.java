package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.Enroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {


    Page<Enroll> findByEnrollEndTimeAfter(LocalDateTime now, Pageable pageable);

}
