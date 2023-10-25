package com.newproject.projectn.controller;

import com.newproject.projectn.Service.UserService;
import com.newproject.projectn.config.security.auth.AuthenticationRequest;
import com.newproject.projectn.config.security.auth.AuthenticationResponse;
import com.newproject.projectn.dto.user.PostUserDto;
import com.newproject.projectn.dto.duplicationCheckDtos.EmailCheckDto;
import com.newproject.projectn.dto.duplicationCheckDtos.NickNameCheckDto;
import com.newproject.projectn.dto.duplicationCheckDtos.UsernameCheckDto;
import com.newproject.projectn.entitiy.Kindergarten;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserService userService;
    UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<User> postUser(@RequestBody PostUserDto postDto){
        User postingUser = mapper.postUserDtoToUserEntity2(postDto);
        postingUser.setImage("https://img.freepik.com/premium-photo/portrait-of-a-handsome-young-man_53876-38137.jpg");//이미지 로직 생성전 임시파일


        User user = userService.register(postingUser, postDto.getDuplicationCheck(), postDto.getCityId(), postDto.getDetails(), postDto.getZipcode());

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/email/check")
    public ResponseEntity<String> checkEmailDuplication(@RequestBody EmailCheckDto emailCheckDto){
        if(userService.checkEmailDuplication(emailCheckDto.getEmail())){
            return new ResponseEntity<>("중복된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
        }
    }
    @PostMapping("/username/check")
    public ResponseEntity<String> checkUsernameDuplication(@RequestBody UsernameCheckDto usernameCheckDto){
        if(userService.checkUsernameDuplication(usernameCheckDto.getUsername())){
            return new ResponseEntity<>("중복된 유저명입니다.", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("사용 가능한 유저명입니다.", HttpStatus.OK);
        }
    }
    @PostMapping("/nickname/check")
    public ResponseEntity<String> checkNickName(@RequestBody NickNameCheckDto nickNameCheckDto){
        if( userService.checkNicknameDuplication(nickNameCheckDto.getNickName())){
            return new ResponseEntity<>("중복된 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>("사용 가능한 닉네임입니다.", HttpStatus.OK);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken( HttpServletRequest request,HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable long userId){

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

        User testUser = mapper.postUserDtoToUserEntity2(test);
        testUser.setUserId(1);//테스트용 임시(patch메서드 생성 필요)
        testUser.setImage("https://img.freepik.com/premium-photo/portrait-of-a-handsome-young-man_53876-38137.jpg");//이미지 로직 생성전 임시파일
        User user = userService.editUser(userId, testUser);
        return new ResponseEntity<User>(user,HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Kindergarten> deleteUser(@PathVariable Long userId){

        userService.removeUser(userId);

        return new ResponseEntity<Kindergarten>(HttpStatus.OK);
    }

}
