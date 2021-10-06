package com.sparta.blogwithcomments.controller;

import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.repository.ContentRepository;
import com.sparta.blogwithcomments.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ContentController {
    private final ContentRepository contentRepository;
    private final ContentService contentService;

    @GetMapping("/api/contents/{id}")
    public String getContents(Model model, @PathVariable Long id) {
        System.out.println("hihi");
        Content content =  contentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        model.addAttribute("username",content.getName());
        model.addAttribute("title",content.getTitle());
        model.addAttribute("contents",content.getContents());
        model.addAttribute("time",content.getCreatedAt());

        return "detail";
    }
}
