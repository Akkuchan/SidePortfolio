package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.post.PatchPostDto;
import com.newproject.projectn.dto.post.PostPostDto;
import com.newproject.projectn.entitiy.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface PostMapper {

    Post postPostDtoToPostEntity(PostPostDto postDto);
    Post patchPostDtoToPostEntity(PatchPostDto patchPostDto);
}