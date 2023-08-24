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
        User user = userService.createUser(postingUser);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }




    @GetMapping()
    public ResponseEntity<User> getUser(){

        User user = userService.findUser();

        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUserList(){

        List<User> userList = userService.findUserList();

        return new ResponseEntity<>(userList,HttpStatus.OK);

    }

    @PatchMapping
    public ResponseEntity<User> patchUser(){
        User user = userService.editUser();
        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

}
