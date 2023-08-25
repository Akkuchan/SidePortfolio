package com.newproject.projectn.controller;

import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.dto.PostUserDto;
import com.newproject.projectn.dto.testDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;
    UserMapper mapper;
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody PostUserDto postDto){
        User postingUser = mapper.postUserDtoToUserEntity(postDto);
        postingUser.setImage("https://img.freepik.com/premium-photo/portrait-of-a-handsome-young-man_53876-38137.jpg");//이미지 로직 생성전 임시파일


        User user = userService.createUser(postingUser);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }




    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId){

        User user = userService.findUser(userId);

        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

    @GetMapping("/list/{page}")
    public ResponseEntity<List<User>> getUserList(@PathVariable int page){

        List<User> userList = userService.findUserList(page-1);

        return new ResponseEntity<>(userList,HttpStatus.OK);

    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable long userId, @RequestBody PostUserDto test) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        User testUser = mapper.postUserDtoToUserEntity(test);
        testUser.setUserId(1);//테스트용 임시(patch메서드 생성 필요)
        testUser.setImage("https://img.freepik.com/premium-photo/portrait-of-a-handsome-young-man_53876-38137.jpg");//이미지 로직 생성전 임시파일
        User user = userService.editUser(userId, testUser);
        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

}
