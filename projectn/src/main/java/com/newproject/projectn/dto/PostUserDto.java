package com.newproject.projectn.dto;

import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.Enum.address.Address;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDto {


    public String username;
    public String email;

    public String nickName;

    public String password;

    public String phoneNumber;

    public UserGrade userType;
    public Address address;

    public Boolean emailAvailable;// 이메일 수신여부
    public Boolean smsAvailable;// sms 수신여부

    public Boolean isMarried;// 기혼여부
    public Boolean isPregnant;// 임신여부
    public Boolean hasChild;// 자녀여부

    public List<Boolean> duplicationCheck;



}
