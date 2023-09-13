package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-13T15:59:32+0900",
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
    public Kindergarten patchKindergartenDtoToKindergartenEntity(PatchKindergartenDto patchKindergartenDto) {
        if ( patchKindergartenDto == null ) {
            return null;
        }

        Kindergarten.KindergartenBuilder kindergarten = Kindergarten.builder();

        if ( patchKindergartenDto.getKindergartenId() != null ) {
            kindergarten.kindergartenId( patchKindergartenDto.getKindergartenId() );
        }
        kindergarten.name( patchKindergartenDto.getName() );
        kindergarten.available( patchKindergartenDto.getAvailable() );
        kindergarten.number( patchKindergartenDto.getNumber() );
        kindergarten.startTime( patchKindergartenDto.getStartTime() );
        kindergarten.endTime( patchKindergartenDto.getEndTime() );
        kindergarten.authorizationDate( patchKindergartenDto.getAuthorizationDate() );
        kindergarten.shuttleBus( patchKindergartenDto.getShuttleBus() );
        kindergarten.homepageUrl( patchKindergartenDto.getHomepageUrl() );
        kindergarten.abolitionDate( patchKindergartenDto.getAbolitionDate() );
        kindergarten.classNum( patchKindergartenDto.getClassNum() );
        kindergarten.classArea( patchKindergartenDto.getClassArea() );
        kindergarten.playGround( patchKindergartenDto.getPlayGround() );
        kindergarten.cctv( patchKindergartenDto.getCctv() );
        kindergarten.teacherNum( patchKindergartenDto.getTeacherNum() );
        kindergarten.capacity( patchKindergartenDto.getCapacity() );
        kindergarten.quota( patchKindergartenDto.getQuota() );

        return kindergarten.build();
    }
}
