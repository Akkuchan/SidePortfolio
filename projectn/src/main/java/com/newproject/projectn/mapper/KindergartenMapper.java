package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.dto.kindergarten.ResponseKindergartenDto;
import com.newproject.projectn.entitiy.Enum.Type;
import com.newproject.projectn.entitiy.Kindergarten;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface KindergartenMapper {

    static Type typeToEnum(String type) {
        switch (type.toUpperCase()) {
            case "민간":
                return Type.PRIVATE;
            case "국공립":
                return Type.PUBLIC;
            case "법인-단체 등":
                return Type.ORGANIZATION;
            case "직장":
                return Type.CORPORATE;
            default:
                return Type.HOME;
        }
    }




    static Kindergarten postKindergartenDtoToKindergartenEntity(PostKindergartenDto postKindergartenDto){
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
        kindergarten.type(typeToEnum(postKindergartenDto.getType()));
        return kindergarten.build();
    }



    Kindergarten patchKindergartenDtoToKindergartenEntity(PatchKindergartenDto patchKindergartenDto);
    static ResponseKindergartenDto KindergartenEntityToResponseKindergartenDto(Kindergarten kindergarten){
        if ( kindergarten == null ) {
            return null;
        }

        ResponseKindergartenDto.ResponseKindergartenDtoBuilder responseDto = ResponseKindergartenDto.builder();

        responseDto.state(kindergarten.getAddress().getCity().getState().getStateName())
                .city(kindergarten.getAddress().getCity().getCityName())
                .name(kindergarten.getName())
                .type(kindergarten.getType().name())
                .number(kindergarten.getNumber()).latitude(kindergarten.getLatitude())
                .longitude(kindergarten.getLongitude())
                .kindergartenId(kindergarten.getKindergartenId());

        return responseDto.build();
    }

}