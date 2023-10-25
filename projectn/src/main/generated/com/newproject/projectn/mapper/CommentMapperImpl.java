package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.comment.PatchCommentDto;
import com.newproject.projectn.dto.comment.PostCommentDto;
import com.newproject.projectn.entitiy.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-24T18:35:59+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment postCommentDtoToCommentEntity(PostCommentDto postCommentDto) {
        if ( postCommentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setBody( postCommentDto.getBody() );

        return comment;
    }

    @Override
    public Comment postCommentDtoToCommentEntity(PatchCommentDto patchCommentDto) {
        if ( patchCommentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( patchCommentDto.getCommentId() != null ) {
            comment.setCommentId( patchCommentDto.getCommentId() );
        }
        comment.setBody( patchCommentDto.getBody() );

        return comment;
    }
}
