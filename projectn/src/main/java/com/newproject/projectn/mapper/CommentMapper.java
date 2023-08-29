package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.comment.PatchCommentDto;
import com.newproject.projectn.dto.comment.PostCommentDto;
import com.newproject.projectn.entitiy.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface CommentMapper {

    Comment patchCommentDtoToCommentEntity(PostCommentDto postCommentDto);
    Comment patchCommentDtoToCommentEntity(PatchCommentDto patchCommentDto);
}