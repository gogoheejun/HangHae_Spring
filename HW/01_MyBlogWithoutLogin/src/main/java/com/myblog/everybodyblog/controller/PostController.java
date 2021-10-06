package com.myblog.everybodyblog.controller;

import com.myblog.everybodyblog.domain.Post;
import com.myblog.everybodyblog.domain.PostRepository;
import com.myblog.everybodyblog.dto.PostRequestDto;
import com.myblog.everybodyblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/postpage")
    public String gotoPostpage(){
        return "createpost";
    }



    @PostMapping("/api/posts")
    public String createPost(@RequestParam PostRequestDto requestDto){
        Post post = new Post(requestDto);
        System.out.println(post);
        postRepository.save(post);
        return "index";
    }

    @ResponseBody
    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/api/posts/{id}")
    public String getPosts(Model model, @PathVariable Long id){
        Optional<Post> apost = postRepository.findById(id);
        model.addAttribute("title",apost.get().getTitle());
        model.addAttribute("username",apost.get().getUsername());
        model.addAttribute("contents",apost.get().getContents());
        model.addAttribute("createdAt",apost.get().getCreatedAt());

        return "onepost";
    }

//    @ResponseBody
//    @PutMapping("/api/posts/{id}")
//    public Long updatePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto){
//        return postService.update(id,requestDto);
//    }
//
//    @ResponseBody
//    @DeleteMapping("/api/posts/{id}")
//    public Long deletePost(@PathVariable Long id){
//        postRepository.deleteById(id);
//        return id;
//    }




}
