package com.myblog.everybodyblog.controller;

import com.myblog.everybodyblog.domain.Post;
import com.myblog.everybodyblog.domain.PostRepository;
import com.myblog.everybodyblog.domain.PostRequestDto;
import com.myblog.everybodyblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);

        return postRepository.save(post);
    }

    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/posts/{id}")
    public Optional<Post> getPosts(@PathVariable Long id){
        Optional<Post> apost = postRepository.findById(id);
//        System.out.println(apost.get().getTitle());
        return apost;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto){
        return postService.update(id,requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
        return id;
    }




}
