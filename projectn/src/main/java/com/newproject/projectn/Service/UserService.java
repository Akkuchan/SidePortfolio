package com.newproject.projectn.Service;

import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.repository.AddressRepository;
import com.newproject.projectn.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    AddressRepository addressRepository;
    UserRepository userRepository;
    @Transactional
    public User createUser(User user) {
        //username, email, 닉네임 등 중복검사 체크 + 중복시 에러 추가하기(Bisness 에러 추가)
        addressRepository.save(user.getAddress());
        return userRepository.save(user);
    }

    public User findUser() {
        return new User();

    }

    public User editUser() {
        return new User();

    }

    public List<User> findUserList() {
        return new ArrayList<>();
    }
}
