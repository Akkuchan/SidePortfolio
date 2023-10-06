package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.Waiting;
import com.newproject.projectn.repository.WaitingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WaitingService {

    WaitingRepository waitingRepository;
    public Waiting createWaiting(Waiting newWaiting) {

        return waitingRepository.save(newWaiting);

    }

    public Waiting findWaiting(Long waitingId) {
        return waitingRepository.findById(waitingId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));

    }

    public Waiting editWaiting() {
        return new Waiting();

    }

    public List<Waiting> findWaitingList(int pageIdx) {
        return waitingRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("updateTime").descending())).stream().toList();


    }

    public List<Waiting> findPedingWaitingList() {
        return waitingRepository.findAll(PageRequest.of(0, 3, Sort.by("updateTime").descending())).stream().toList();

    }
}
