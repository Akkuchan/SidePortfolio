package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String findByUsername);
    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    boolean existsByNickName(String email);

    @Query("select u from User u where u.userId IN (:userIdList)")
    Optional<Set<User>> findByIdIn(@Param("userIdList") List<String> userIdList);
}
