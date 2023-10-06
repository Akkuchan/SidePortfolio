package com.newproject.projectn.Service.post;

import com.newproject.projectn.Service.Util.FieldDirtyCheck;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.ParentingProduct;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.ParentingProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ParentingProductService{




    UserRepository userRepository;
    ParentingProductRepository parentingProductRepository;
    FieldDirtyCheck fieldDirtyCheck;
    public ParentingProduct createParentingProduct(ParentingProduct parentingProduct, long userId) {

        User writer = userRepository.findById(userId).orElseThrow();
        parentingProduct.setPostUser(writer);


        return parentingProductRepository.save(parentingProduct);
    }

    public ParentingProduct findParentingProduct(Long postId) {

        return parentingProductRepository.findById(postId).orElseThrow();
    }

    public List<ParentingProduct> findPostList(int pageIdx, int postPerpage) {// 오늘의글 가져오기
        return parentingProductRepository.findAll(PageRequest.of(pageIdx, postPerpage, Sort.by("updateTime").descending()))
                .stream().toList();

    }




    public ParentingProduct editParentingProduct(ParentingProduct editParentingProduct) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ParentingProduct foundPost = parentingProductRepository.findById(editParentingProduct.getPostId()).orElseThrow();
        fieldDirtyCheck.dirtyCheck(editParentingProduct, foundPost);// 필드 변경사항 있는지 체크 후 삽입
        return parentingProductRepository.save(foundPost);
    }





    public void deleteParentingProduct(Long postId) {
        parentingProductRepository.deleteById(postId);
    }
}
