package com.newproject.projectn.Service.post;

import com.newproject.projectn.Service.Util.FieldDirtyCheck;
import com.newproject.projectn.dto.post.FaqDtos;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.FAQ;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.FaqRepository;
import com.newproject.projectn.repository.post.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FaqService {
    PostRepository postRepository;
    UserRepository userRepository;
    FaqRepository faqRepository;
    FieldDirtyCheck fieldDirtyCheck;
    @Transactional
    public FAQ createFaq(FAQ newFaq, long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        newFaq.setPostUser(user);
       return faqRepository.save(newFaq);

    }

    public FAQ findFaq(Long faqId) {
        FAQ foundFaq = faqRepository.findById(faqId).orElseThrow();
        foundFaq.setViewPlus1();
        return faqRepository.save(foundFaq);
    }

    public List<FaqDtos.ListResponseDto> findFaqList(int pageIdx, int elementPerPage) {
        List<FaqDtos.ListResponseDto> faqList = faqRepository.findAll(PageRequest.of(pageIdx - 1, elementPerPage, Sort.by("regTime").descending()))
                .stream()
                .map((element) -> FaqDtos.ListResponseDto.builder()
                        .title(element.getTitle())
                        .body(element.getBody())
                        .regTime(element.getRegTime())
                        .recommend(element.getRecommend())
                        .view(element.getView())
                        .build())
                        .toList();

        return faqList;
    }


    @Transactional
    public FAQ editFaq(FAQ editFaq) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        FAQ foundFaq = faqRepository.findById(editFaq.getPostId()).orElseThrow();

        fieldDirtyCheck.dirtyCheck(editFaq, foundFaq);// 필드 변경사항 있는지 체크 후 삽입

       return faqRepository.save(editFaq);
    }



    @Transactional
    public void deleteFaq(Long faqId) {

        faqRepository.deleteById(faqId);

    }
}
