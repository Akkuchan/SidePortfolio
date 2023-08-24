package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.PostUserDto;
import com.newproject.projectn.entitiy.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-24T15:38:04+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User postUserDtoToUserEntity(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setAddress( postUserDto.getAddress() );

        return user;
    }
}
