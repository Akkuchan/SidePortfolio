package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.PostUserDto;
import com.newproject.projectn.entitiy.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User postUserDtoToUserEntity(PostUserDto postUserDto);
}