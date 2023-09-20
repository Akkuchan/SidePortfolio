package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FAQ, Long> {
}
