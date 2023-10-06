package com.newproject.projectn.Service.post;

import com.newproject.projectn.Service.Util.FieldDirtyCheck;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.entitiy.post.ThriftShopProduct;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.ThriftShopProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ThriftShopProductService {

    UserRepository userRepository;
    ThriftShopProductRepository thriftShopProductRepository;
    FieldDirtyCheck fieldDirtyCheck;
    public ThriftShopProduct createThriftShopProduct(ThriftShopProduct thriftShopProduct, long userId) {

        User writer = userRepository.findById(userId).orElseThrow();
        thriftShopProduct.setPostUser(writer);


        return thriftShopProductRepository.save(thriftShopProduct);
    }

    public ThriftShopProduct findThriftShopProduct(Long postId) {

        return thriftShopProductRepository.findById(postId).orElseThrow();
    }

    public List<ThriftShopProduct> findThriftShopProductList(int pageIdx, int postPerpage) {// 오늘의글 가져오기
        return thriftShopProductRepository.findAll(PageRequest.of(pageIdx, postPerpage, Sort.by("updateTime").descending()))
                .stream().toList();

    }



    public ThriftShopProduct editThriftShopProduct(ThriftShopProduct editThriftShopProduct) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ThriftShopProduct foundPost = thriftShopProductRepository.findById(editThriftShopProduct.getPostId()).orElseThrow();
        fieldDirtyCheck.dirtyCheck(editThriftShopProduct, foundPost);// 필드 변경사항 있는지 체크 후 삽입
        return thriftShopProductRepository.save(foundPost);
    }

    public void deleteThriftShopProduct(Long postId) {
        thriftShopProductRepository.deleteById(postId);
    }

}
