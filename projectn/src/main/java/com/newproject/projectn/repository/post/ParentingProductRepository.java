package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.LocalInfo;
import com.newproject.projectn.entitiy.post.ParentingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentingProductRepository extends JpaRepository<ParentingProduct, Long> {
}
