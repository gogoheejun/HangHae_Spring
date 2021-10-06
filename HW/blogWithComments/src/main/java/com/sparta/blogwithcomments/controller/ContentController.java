package com.sparta.blogwithcomments.controller;

import com.sparta.blogwithcomments.model.Comment;
import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.repository.CommentRepository;
import com.sparta.blogwithcomments.repository.ContentRepository;
import com.sparta.blogwithcomments.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController {
    private final ContentRepository contentRepository;
    private final ContentService contentService;

    private final CommentRepository commentRepository;

    //글(content) 보여주기
    @GetMapping("/api/contents/{id}")
    public String getContents(Model model, @PathVariable Long id) {
        Content content =  contentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        model.addAttribute("contentId",id);
        model.addAttribute("username",content.getName());
        model.addAttribute("title",content.getTitle());
        model.addAttribute("contents",content.getContents());
        model.addAttribute("time",content.getCreatedAt());

        //댓글목록도 보여줘야 함.
        List<Comment> commentList = commentRepository.findAllByContentIdOrderByCreatedAtDesc(id);
        if(commentList.size() >0){
            System.out.println(commentList.get(0).getUser().getUsername());
            System.out.println(commentList.get(0).getComment());
            System.out.println(commentList.get(0).getCreatedAt());
        }
        model.addAttribute("commentList",commentList);

        return "detail";
    }
}
