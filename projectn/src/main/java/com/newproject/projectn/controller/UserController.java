package com.newproject.projectn.controller;

import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody Kindergarten kindergarten){

        User user = userService.createUser();
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(){

        User user = userService.findUser();

        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

    @GetMapping("/users")
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
