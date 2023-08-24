package com.newproject.projectn.controller;

import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<User> postKindergarten(@RequestBody Kindergarten){

        User user = userService.createUser();
        return new ResponseEntity<User>();
    }

    @GetMapping
    public ResponseEntity<User> getKindergarten(){

        User user = userService.findUser();

        return new ResponseEntity<User>();
    }

    @GetMapping
    public ResponseEntity<User> getKindergartenList(){

        List<User> userList = userService.findUserList();

        return new ResponseEntity<User>();
    }

    @PatchMapping
    public ResponseEntity<User> patchKindergarten(){
        User user = userService.editUser();
        return new ResponseEntity<User>();
    }

}
