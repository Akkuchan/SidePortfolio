package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.ParentingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentingEventRepository extends JpaRepository<ParentingEvent, Long> {
}
