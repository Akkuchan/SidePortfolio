package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.waiting.PostWaitingDto;
import com.newproject.projectn.entitiy.Waiting;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-13T15:59:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class WaitingMapperImpl implements WaitingMapper {

    @Override
    public Waiting postWaitingDtoToWaitingEntity(PostWaitingDto postWaitingDto) {
        if ( postWaitingDto == null ) {
            return null;
        }

        Waiting waiting = new Waiting();

        return waiting;
    }
}
