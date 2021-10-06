package com.myblog.everybodyblog.service;

import com.myblog.everybodyblog.domain.Post;
import com.myblog.everybodyblog.domain.PostRepository;
import com.myblog.everybodyblog.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("아이디가 존재하지 않음")
        );
        post.update(requestDto);
        return post.getId();
    }
}
