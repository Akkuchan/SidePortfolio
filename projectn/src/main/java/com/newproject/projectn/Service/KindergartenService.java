package com.newproject.projectn.Service;

import com.newproject.projectn.entitiy.Kindergarten;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KindergartenService {
    public Kindergarten createKindergarten() {
        return new Kindergarten();
    }

    public Kindergarten findKindergarten() {
        return new Kindergarten();
    }

    public List<Kindergarten> findKindergartenList() {
        return new ArrayList<>();
    }

    public Kindergarten editKindergarten() {
        return new Kindergarten();
    }
}
