package com.newproject.projectn.Service.post;

import com.newproject.projectn.Service.Util.FieldDirtyCheck;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.post.LocalInfo;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.mapper.PostMapper;
import com.newproject.projectn.repository.CityRepository;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.LocalInfoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class LocalInfoService  {

    UserRepository userRepository;
    LocalInfoRepository localInfoRepository;
    CityRepository cityRepository;
    PostMapper mapper;
    FieldDirtyCheck fieldDirtyCheck;
    public LocalInfo createLocalInfo(LocalInfo localInfo, long userId, long CityId) {

        User writer = userRepository.findById(userId).orElseThrow();
        City city = cityRepository.findById(CityId).orElseThrow();
        localInfo.setPostUser(writer);
        localInfo.setCity(city);

        return localInfoRepository.save(localInfo);
    }

    public LocalInfo findLocalInfo(Long faqId) {

       return localInfoRepository.findById(faqId).orElseThrow();
    }

    public LocalInfo editLocalInfo(LocalInfo editLocalInfo) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Post foundLocalInfo = localInfoRepository.findById(editLocalInfo.getPostId()).orElseThrow();
        fieldDirtyCheck.dirtyCheck(editLocalInfo, foundLocalInfo);// 필드 변경사항 있는지 체크 후 삽입
        return localInfoRepository.save(editLocalInfo);
    }

    public void deleteLocalInfo(Long faqId) {
        localInfoRepository.deleteById(faqId);
    }


    public List<LocalInfo> findLocalInfoList(int pageIdx, int postPerPage) {
        return localInfoRepository.findAll(PageRequest.of(pageIdx, postPerPage, Sort.by("updateTime").descending()))
                .stream().toList();
    }
}
