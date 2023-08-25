package com.newproject.projectn.User;


import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void test() {
        LocalDate testDate1 = LocalDate.now();
        LocalDateTime testDate2 = LocalDateTime.now();

        User testUser = User.builder()
                .username("pohd8494")
                .email("pohd8494@naver.com")
                .nickName("testMan")
                .password("testPassword")
                .phoneNumber("010-1234-5678")
                .userType(UserGrade.NORMAL_USER)
                .isEmailAvailable(true)
                .isSmsAvailable(true)
                .isMarried(true)
                .isPregnant(false)
                .isParent(false)
                .build();

        User createdUser = userService.createUser(testUser);


        assertEquals("pohd8494@naver.com", createdUser.getEmail());

    }
}
