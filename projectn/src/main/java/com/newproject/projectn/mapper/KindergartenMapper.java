package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.kindergarten.PatchKindergartenDto;
import com.newproject.projectn.dto.kindergarten.PostKindergartenDto;
import com.newproject.projectn.entitiy.Kindergarten;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface KindergartenMapper {

    Kindergarten postKindergartenDtoToKindergartenEntity(PostKindergartenDto postKindergartenDto);
    Kindergarten patchKindergartenDtoToKindergartenEntity(PatchKindergartenDto postKindergartenDto);
}