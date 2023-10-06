package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    Page<Post> findAllByPostUser(User user, Pageable pageRequest);
}
