package com.newproject.projectn.Service;

import com.newproject.projectn.entitiy.Waiting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaitingService {
    public Waiting createWaiting() {
        return new Waiting();
    }

    public Waiting findWaiting() {
        return new Waiting();

    }

    public Waiting editWaiting() {
        return new Waiting();

    }

    public List<Waiting> findWaitingList() {
        return new ArrayList<>();

    }
}
