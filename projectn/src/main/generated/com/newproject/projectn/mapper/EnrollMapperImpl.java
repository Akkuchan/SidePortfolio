package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.enroll.EnrollDtos;
import com.newproject.projectn.entitiy.Enroll;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-06T21:13:38+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class EnrollMapperImpl implements EnrollMapper {

    @Override
    public Enroll postEnrollDtoToEnrollEntity(EnrollDtos.PostEnrollDto postEnrollDto) {
        if ( postEnrollDto == null ) {
            return null;
        }

        Enroll enroll = new Enroll();

        enroll.setEnrollStartTime( postEnrollDto.getEnrollStartTime() );
        enroll.setEnrollEndTime( postEnrollDto.getEnrollEndTime() );

        return enroll;
    }
}
