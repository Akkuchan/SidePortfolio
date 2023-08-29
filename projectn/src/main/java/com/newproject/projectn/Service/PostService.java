package com.newproject.projectn.Service;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.Post;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.repository.PostRepository;
import com.newproject.projectn.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    PostRepository postRepository;
    public Post createPost(Post post) {

        return postRepository.save(post);

    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_POST_ENTITY));

    }

    public List<Post> findPostList(int pageIdx) {
        return postRepository.findAll(PageRequest.of(pageIdx, 30, Sort.by("updateTime").descending()))
        .stream().toList();

    }

    public Post editPost(Post editingPost) {
        Post existingPost= postRepository.findById(editingPost.getPostId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_POST_ENTITY));
        if(editingPost.getPostUser().getUserId()!=existingPost.getPostUser().getUserId()) {
            throw new BusinessLogicException(ExceptionCode.CONFLICT_USER_DATA);
        }
        return postRepository.save(editingPost);
    }
}
