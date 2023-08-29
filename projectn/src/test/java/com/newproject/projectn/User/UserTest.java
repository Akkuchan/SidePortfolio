package com.newproject.projectn.User;


import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.Enum.address.Address;
import com.newproject.projectn.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.newproject.projectn.entitiy.Enum.address.State.INCHEON;
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
                .emailAvailable(true)
                .smsAvailable(true)
                .isMarried(true)
                .isPregnant(false)
                .hasChild(false)
                .address(new Address(INCHEON,"중구","32231"))
                .build();
        List<Boolean> test = new ArrayList<Boolean>();
        test.add(true);
        test.add(true);
        test.add(true);
        User createdUser = userService.createUser(testUser,test);


        assertEquals("pohd8494@naver.com", createdUser.getEmail());

    }
}
