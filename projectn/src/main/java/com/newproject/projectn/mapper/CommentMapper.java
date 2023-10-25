package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.comment.CommentResponseDto;
import com.newproject.projectn.dto.comment.PatchCommentDto;
import com.newproject.projectn.dto.comment.PostCommentDto;
import com.newproject.projectn.entitiy.Comment;
import com.newproject.projectn.entitiy.post.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")// 매핑 인터페이스가 스프링의 매퍼라고 인식시킴
public interface CommentMapper {

    Comment postCommentDtoToCommentEntity(PostCommentDto postCommentDto);
    Comment postCommentDtoToCommentEntity(PatchCommentDto patchCommentDto);

    static CommentResponseDto commentEntityToResponseDto(Post post, Comment comment){

        if ( comment == null ) {
            return null;
        }

        CommentResponseDto.CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        commentResponseDto.commentId( comment.getCommentId() );
        commentResponseDto.postId(post.getPostId());
        commentResponseDto.userId(comment.getCommentingUser().getUserId());
        commentResponseDto.userNickName(comment.getCommentingUser().getNickName());
        commentResponseDto.recommend(comment.getRecommend());
        commentResponseDto.regTime( comment.getRegTime() );
        commentResponseDto.body( comment.getBody() );


            return commentResponseDto.build();
        }
};
