package com.newproject.projectn.Service.post;

import com.newproject.projectn.config.exception.BusinessLogicException;
import com.newproject.projectn.config.exception.ExceptionCode;
import com.newproject.projectn.entitiy.User;
import com.newproject.projectn.entitiy.post.Post;
import com.newproject.projectn.repository.UserRepository;
import com.newproject.projectn.repository.post.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    UserRepository userRepository;
    PostRepository postRepository;
    public Post createPost(Post post) {

        return postRepository.save(post);

    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_POST_ENTITY));

    }

    public List<Post> findPostList(int pageIdx, int postPerpage) {// 오늘의글 가져오기
        return postRepository.findAll(PageRequest.of(pageIdx, postPerpage, Sort.by("updateTime").descending()))
        .stream().toList();

    }

    public List<Post> findPopularList() {//인기글 4개 가져오기
        return postRepository.findAll(PageRequest.of(0, 4, Sort.by("recommend", "regTime").descending()))
                .stream().toList();

    }

    public List<Post> findSpecificUserPostList(Long userId, int pageIdx) {//공지사항 가져오기 - 특정 user가 쓴 글 가져오기(1개만)ㅋ
        User writer = userRepository.findById(userId).orElseThrow();
        return postRepository.findAllByPostUser(writer, PageRequest.of(pageIdx-1, 10, Sort.by("regTime").descending())).stream().toList();


    }



    public Post editPost(Post editingPost) {
        Post existingPost= postRepository.findById(editingPost.getPostId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_POST_ENTITY));
        if(editingPost.getPostUser().getUserId()!=existingPost.getPostUser().getUserId()) {
            throw new BusinessLogicException(ExceptionCode.CONFLICT_USER_DATA);
        }
        return postRepository.save(editingPost);
    }

    public void removePost(long postId) {
        postRepository.findById(postId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NO_SUCH_ELEMENT));
        postRepository.deleteById(postId);
    }
}
