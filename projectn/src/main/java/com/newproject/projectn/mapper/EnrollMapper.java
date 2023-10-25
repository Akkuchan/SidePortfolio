package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.enroll.EnrollDtos;
import com.newproject.projectn.dto.region.PostStateDto;
import com.newproject.projectn.entitiy.Enroll;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.State;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface EnrollMapper {

    static EnrollDtos.ResultForMain  enrollEntityToResultDtos(Enroll enroll){

            if ( enroll == null ) {
                return null;
            }

        EnrollDtos.ResultForMain.ResultForMainBuilder resultDtoBuilder = EnrollDtos.ResultForMain.builder();
        resultDtoBuilder.enrollId(enroll.getEnrollId()).title(enroll.getKindergarten().getName() + "   ---  곧 마감 예정!!").dueTime(enroll.getEnrollEndTime().toString());


            return resultDtoBuilder.build();
        }

    Enroll postEnrollDtoToEnrollEntity(EnrollDtos.PostEnrollDto postEnrollDto);

    static EnrollDtos.ResultDto EnrollEntityToResponseDto(Enroll enrollEntity){
        if ( enrollEntity == null ) {
            return null;
        }

        EnrollDtos.ResultDto.ResultDtoBuilder resultDto = EnrollDtos.ResultDto.builder();

        if ( enrollEntity.getEnrollId() != null ) {
            resultDto.enrollId( enrollEntity.getEnrollId() );
        }
        resultDto.responseKindergartenDto(KindergartenMapper.KindergartenEntityToResponseKindergartenDto(enrollEntity.getKindergarten()));
        resultDto.enrollId(enrollEntity.getEnrollId());
        resultDto.enrollStartTime( enrollEntity.getEnrollStartTime() );
        resultDto.enrollEndTime( enrollEntity.getEnrollEndTime() );

        return resultDto.build();

    }




    static EnrollDtos.ResultForList  enrollEntityToResultListDtos(Enroll enroll){

        if ( enroll == null ) {
            return null;
        }
        String enrollDate = enroll.getEnrollStartTime().toString() + "~" + enroll.getEnrollEndTime().toString();


        EnrollDtos.ResultForList.ResultForListBuilder resultDtoBuilder = EnrollDtos.ResultForList.builder();
        resultDtoBuilder.enrollId(enroll.getEnrollId()).title(enroll.getKindergarten().getName() + "곧 마감 예정!!").enrollDate(enrollDate).closed(enroll.getEnrollEndTime().isBefore(LocalDateTime.now()));
        return resultDtoBuilder.build();
    }
};




