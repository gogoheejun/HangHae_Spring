package com.sparta.blogwithcomments.controller;

import com.sparta.blogwithcomments.dto.CommentRequestDto;
import com.sparta.blogwithcomments.model.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    //나중에 여기서 댓글저장하고, 불러와서 응답하는거 다 할것임
//    @PostMapping("api/comment")
//    public List<Comment> addComment(
//            @RequestBody CommentRequestDto commentRequestDto
//            )
}
