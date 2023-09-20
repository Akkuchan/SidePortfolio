package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.user.PostUserDto;
import com.newproject.projectn.entitiy.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface UserMapper {

    User postUserDtoToUserEntity2(PostUserDto postUserDto);

}