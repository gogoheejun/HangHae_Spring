package com.sparta.blogwithcomments.controller;

import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.model.ContentRequestDto;
import com.sparta.blogwithcomments.repository.ContentRepository;
import com.sparta.blogwithcomments.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentRestController {
    private final ContentRepository contentRepository;
    private final ContentService contentService;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Content> getContents() {
        return contentRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/contents/{id}")
    public Content getContents(@PathVariable Long id) {
        Content content =  contentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return content;
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Content createContent(@RequestBody ContentRequestDto requestDto) {
        Content content = new Content(requestDto);
        return contentRepository.save(content);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContent(@PathVariable Long id, @RequestBody ContentRequestDto requestDto) {
        contentService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContent(@PathVariable Long id) {
        contentRepository.deleteById(id);
        return id;
    }

}
