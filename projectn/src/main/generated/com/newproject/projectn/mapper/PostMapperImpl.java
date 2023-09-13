package com.newproject.projectn.mapper;

import com.newproject.projectn.dto.post.PatchPostDto;
import com.newproject.projectn.dto.post.PostPostDto;
import com.newproject.projectn.entitiy.Post;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-13T15:59:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postPostDtoToPostEntity(PostPostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        if ( post.getImgList() != null ) {
            List<String> list = postDto.getImgList();
            if ( list != null ) {
                post.getImgList().addAll( list );
            }
        }
        if ( post.getTags() != null ) {
            List<String> list1 = postDto.getTags();
            if ( list1 != null ) {
                post.getTags().addAll( list1 );
            }
        }

        return post;
    }

    @Override
    public Post patchPostDtoToPostEntity(PatchPostDto patchPostDto) {
        if ( patchPostDto == null ) {
            return null;
        }

        Post post = new Post();

        if ( post.getImgList() != null ) {
            List<String> list = patchPostDto.getImgList();
            if ( list != null ) {
                post.getImgList().addAll( list );
            }
        }
        if ( post.getTags() != null ) {
            List<String> list1 = patchPostDto.getTags();
            if ( list1 != null ) {
                post.getTags().addAll( list1 );
            }
        }

        return post;
    }
}
