package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



}
