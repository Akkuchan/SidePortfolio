package com.newproject.projectn.repository;

import com.newproject.projectn.entitiy.Post;
import com.newproject.projectn.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
