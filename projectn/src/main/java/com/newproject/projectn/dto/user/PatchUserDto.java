package com.newproject.projectn.dto.user;

import com.newproject.projectn.entitiy.Enum.UserGrade;
import com.newproject.projectn.entitiy.Enum.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDto {


    public String username;
    public String email;

    public String nickName;

    public String password;

    public String phoneNumber;

    public UserGrade userType;
    public Address address;

    public boolean emailAvailable;// 이메일 수신여부
    public boolean smsAvailable;// sms 수신여부

    public boolean isMarried;// 기혼여부
    public boolean isPregnant;// 임신여부
    public boolean hasChild;// 자녀여부



}
