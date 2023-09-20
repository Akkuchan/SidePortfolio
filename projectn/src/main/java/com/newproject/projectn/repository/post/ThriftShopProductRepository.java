package com.newproject.projectn.repository.post;

import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.entitiy.post.ThriftShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThriftShopProductRepository extends JpaRepository<ThriftShopProduct, Long> {



}
