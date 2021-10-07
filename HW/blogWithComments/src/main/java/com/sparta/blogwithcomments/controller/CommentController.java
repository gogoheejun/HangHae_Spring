package com.sparta.blogwithcomments.controller;


import com.sparta.blogwithcomments.dto.CommentRequestDto;
import com.sparta.blogwithcomments.model.Comment;
import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.repository.CommentRepository;
import com.sparta.blogwithcomments.repository.ContentRepository;
import com.sparta.blogwithcomments.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
//   댓글 저장하기
    @PostMapping("api/comment")
    public String addComment(
            @RequestParam String comment,
            @RequestParam Long contentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Model model
        ) {
        Optional<Content> thisContent = contentRepository.findById(contentId);
        Comment thisComment = new Comment(thisContent.get(), userDetails.getUser(), comment);
        commentRepository.save(thisComment);
        String url = "/api/contents/"+contentId;
        return "redirect:"+url;
    }
    //댓글불러오기


}
