package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    boolean existsByNickname(String email);
}
