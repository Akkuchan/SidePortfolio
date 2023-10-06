package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.enroll.EnrollDtos;
import com.newproject.projectn.dto.region.PostStateDto;
import com.newproject.projectn.entitiy.Enroll;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.address.State;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")// 매

// 핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface EnrollMapper {

    static EnrollDtos.ResultForMain  enrollEntityToResultDtos(Enroll enroll){

            if ( enroll == null ) {
                return null;
            }

        EnrollDtos.ResultForMain.ResultForMainBuilder resultDtoBuilder = EnrollDtos.ResultForMain.builder();
        resultDtoBuilder.title(enroll.getKindergarten().getName() + "곧 마감 예정!!").link("testtest").dueTime(enroll.getEnrollEndTime().toString());
            return resultDtoBuilder.build();
        }

    Enroll postEnrollDtoToEnrollEntity(EnrollDtos.PostEnrollDto postEnrollDto);

    static EnrollDtos.ResultForList  enrollEntityToResultListDtos(Enroll enroll){

        if ( enroll == null ) {
            return null;
        }
        String enrollDate = enroll.getEnrollStartTime().toString() + "~" + enroll.getEnrollEndTime().toString();


        EnrollDtos.ResultForList.ResultForListBuilder resultDtoBuilder = EnrollDtos.ResultForList.builder();
        resultDtoBuilder.title(enroll.getKindergarten().getName() + "곧 마감 예정!!").enrollDate(enrollDate).closed(enroll.getEnrollEndTime().isBefore(LocalDateTime.now()));
        return resultDtoBuilder.build();
    }
};




