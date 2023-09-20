package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.LocalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalInfoRepository  extends JpaRepository<LocalInfo, Long> {
}
