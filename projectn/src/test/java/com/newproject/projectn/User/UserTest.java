package com.newproject.projectn.User;


import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.address.Address;
import com.newproject.projectn.entitiy.address.City;
import com.newproject.projectn.entitiy.address.State;
import com.newproject.projectn.entitiy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


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
//                .address(new Address(new City(1L, "중구",new State(1L,"서울특별시")),"서울로 111 101-1001호","43423"))
                .build();
        List<Boolean> test = new ArrayList<Boolean>();
        test.add(true);
        test.add(true);
        test.add(true);
        User createdUser = userService.createUser(testUser,test, 28L, "상세주소", "11111");


        assertEquals("pohd8494@naver.com", createdUser.getEmail());

    }
}
