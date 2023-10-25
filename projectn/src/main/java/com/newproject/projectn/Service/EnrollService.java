package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.dto.Multi_ResponseDTO;
import com.newproject.projectn.entitiy.Enroll;
import com.newproject.projectn.repository.EnrollRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Service
@AllArgsConstructor
public class EnrollService {

    EnrollRepository enrollRepository;

    public List<Enroll> findEnding3Enrollments(){

        Page<Enroll> enrollList = getEnrolls(0,3, "enrollEndTime");
        return enrollList.getContent();
    }

    public Page<Enroll> findEnrollList(int pageIdx, int pageSize){
        return getEnrolls(pageIdx,pageSize, "enrollEndTime");
    }

    public Enroll findEnroll(long enrollId){
           return enrollRepository.findById(enrollId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
    }
    public Enroll createEnroll(Enroll newEnroll) {
        return enrollRepository.save(newEnroll);
    }

    public Enroll editStartTime(Long enrollId, LocalDateTime enrollStartTime) {
        Enroll foundEnroll = enrollRepository.findById(enrollId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        foundEnroll.setEnrollStartTime(enrollStartTime);
        return enrollRepository.save(foundEnroll);
    }

    public Enroll editEndTime(Long enrollId, LocalDateTime enrollStartTime) {
        Enroll foundEnroll = enrollRepository.findById(enrollId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        foundEnroll.setEnrollEndTime(enrollStartTime);
        return enrollRepository.save(foundEnroll);
    }

    private Page<Enroll> getEnrolls(int pageIdx, int pageSize, String sortOption) {
        return enrollRepository.findAll(PageRequest.of(pageIdx-1, pageSize, Sort.by(sortOption).descending()));
    }

    public Page<Enroll> getThreePendingEnrolls( ) {

        return enrollRepository.findByEnrollEndTimeAfter(LocalDateTime.now(), PageRequest.of(0, 3, Sort.by("enrollEndTime").descending()));
    }


}
