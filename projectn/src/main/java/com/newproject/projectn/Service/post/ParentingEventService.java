package com.newproject.projectn.Service.post;

import com.newproject.projectn.Service.Util.FieldDirtyCheck;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.post.LocalInfo;
import com.newproject.projectn.entitiy.post.ParentingEvent;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.mapper.PostMapper;
import com.newproject.projectn.repository.CityRepository;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.LocalInfoRepository;
import com.newproject.projectn.repository.post.ParentingEventRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ParentingEventService {


    UserRepository userRepository;
    ParentingEventRepository parentingEventRepository;
    FieldDirtyCheck fieldDirtyCheck;
    public ParentingEvent createParentingEvent(ParentingEvent parentingEvent, long userId) {

        User writer = userRepository.findById(userId).orElseThrow();
        parentingEvent.setPostUser(writer);


        return parentingEventRepository.save(parentingEvent);
    }

    public ParentingEvent findParentingEvent(Long postId) {

        return parentingEventRepository.findById(postId).orElseThrow();
    }



    public List<ParentingEvent> findParentingEventList(int pageIdx, int postPerpage) {
        return parentingEventRepository.findAll(PageRequest.of(pageIdx, postPerpage, Sort.by("updateTime").descending()))
                .stream().toList();

    }


    public ParentingEvent editParentingEvent(ParentingEvent editLocalInfo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ParentingEvent foundLocalInfo = parentingEventRepository.findById(editLocalInfo.getPostId()).orElseThrow();
        fieldDirtyCheck.dirtyCheck(editLocalInfo, foundLocalInfo);// 필드 변경사항 있는지 체크 후 삽입
        return parentingEventRepository.save(foundLocalInfo);
    }

    public void deleteParentingEvent(Long postId) {
        parentingEventRepository.deleteById(postId);
    }



}
