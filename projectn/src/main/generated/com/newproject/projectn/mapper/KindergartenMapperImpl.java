package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-29T19:06:54+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class KindergartenMapperImpl implements KindergartenMapper {

    @Override
    public Kindergarten postKindergartenDtoToKindergartenEntity(PostKindergartenDto postKindergartenDto) {
        if ( postKindergartenDto == null ) {
            return null;
        }

        Kindergarten.KindergartenBuilder kindergarten = Kindergarten.builder();

        kindergarten.name( postKindergartenDto.getName() );
        kindergarten.address( postKindergartenDto.getAddress() );
        kindergarten.latitude( postKindergartenDto.getLatitude() );
        kindergarten.longitude( postKindergartenDto.getLongitude() );
        kindergarten.available( postKindergartenDto.getAvailable() );
        kindergarten.number( postKindergartenDto.getNumber() );
        kindergarten.startTime( postKindergartenDto.getStartTime() );
        kindergarten.endTime( postKindergartenDto.getEndTime() );
        kindergarten.authorizationDate( postKindergartenDto.getAuthorizationDate() );
        kindergarten.shuttleBus( postKindergartenDto.getShuttleBus() );
        kindergarten.homepageUrl( postKindergartenDto.getHomepageUrl() );
        kindergarten.abolitionDate( postKindergartenDto.getAbolitionDate() );
        kindergarten.classNum( postKindergartenDto.getClassNum() );
        kindergarten.classArea( postKindergartenDto.getClassArea() );
        kindergarten.playGround( postKindergartenDto.getPlayGround() );
        kindergarten.cctv( postKindergartenDto.getCctv() );
        kindergarten.teacherNum( postKindergartenDto.getTeacherNum() );
        kindergarten.capacity( postKindergartenDto.getCapacity() );
        kindergarten.quota( postKindergartenDto.getQuota() );

        return kindergarten.build();
    }

    @Override
    public Kindergarten patchKindergartenDtoToKindergartenEntity(PatchKindergartenDto postKindergartenDto) {
        if ( postKindergartenDto == null ) {
            return null;
        }

        Kindergarten.KindergartenBuilder kindergarten = Kindergarten.builder();

        if ( postKindergartenDto.getKindergartenId() != null ) {
            kindergarten.kindergartenId( postKindergartenDto.getKindergartenId() );
        }
        kindergarten.name( postKindergartenDto.getName() );
        kindergarten.address( postKindergartenDto.getAddress() );
        kindergarten.available( postKindergartenDto.getAvailable() );
        kindergarten.number( postKindergartenDto.getNumber() );
        kindergarten.startTime( postKindergartenDto.getStartTime() );
        kindergarten.endTime( postKindergartenDto.getEndTime() );
        kindergarten.authorizationDate( postKindergartenDto.getAuthorizationDate() );
        kindergarten.shuttleBus( postKindergartenDto.getShuttleBus() );
        kindergarten.homepageUrl( postKindergartenDto.getHomepageUrl() );
        kindergarten.abolitionDate( postKindergartenDto.getAbolitionDate() );
        kindergarten.classNum( postKindergartenDto.getClassNum() );
        kindergarten.classArea( postKindergartenDto.getClassArea() );
        kindergarten.playGround( postKindergartenDto.getPlayGround() );
        kindergarten.cctv( postKindergartenDto.getCctv() );
        kindergarten.teacherNum( postKindergartenDto.getTeacherNum() );
        kindergarten.capacity( postKindergartenDto.getCapacity() );
        kindergarten.quota( postKindergartenDto.getQuota() );

        return kindergarten.build();
    }
}
