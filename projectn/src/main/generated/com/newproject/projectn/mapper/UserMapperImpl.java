package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.user.PostUserDto;
import com.newproject.projectn.entitiy.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-20T18:48:09+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User postUserDtoToUserEntity2(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( postUserDto.getUsername() );
        user.email( postUserDto.getEmail() );
        user.nickName( postUserDto.getNickName() );
        user.password( postUserDto.getPassword() );
        user.phoneNumber( postUserDto.getPhoneNumber() );
        user.userType( postUserDto.getUserType() );
        user.emailAvailable( postUserDto.getEmailAvailable() );
        user.smsAvailable( postUserDto.getSmsAvailable() );
        user.isMarried( postUserDto.getIsMarried() );
        user.isPregnant( postUserDto.getIsPregnant() );
        user.hasChild( postUserDto.getHasChild() );

        return user.build();
    }
}
