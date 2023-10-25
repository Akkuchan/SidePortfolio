package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.user.PostUserDto;
import com.newproject.projectn.entitiy.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-24T18:35:59+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User postUserDtoToUserEntity2(PostUserDto postUserDto) {
        if ( postUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( postUserDto.getUsername() );
        user.setEmail( postUserDto.getEmail() );
        user.setNickName( postUserDto.getNickName() );
        user.setPassword( postUserDto.getPassword() );
        user.setPhoneNumber( postUserDto.getPhoneNumber() );
        user.setUserType( postUserDto.getUserType() );
        user.setEmailAvailable( postUserDto.getEmailAvailable() );
        user.setSmsAvailable( postUserDto.getSmsAvailable() );
        user.setIsMarried( postUserDto.getIsMarried() );
        user.setIsPregnant( postUserDto.getIsPregnant() );
        user.setHasChild( postUserDto.getHasChild() );

        return user;
    }
}
