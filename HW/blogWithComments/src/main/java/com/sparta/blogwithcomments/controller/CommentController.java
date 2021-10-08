package com.sparta.blogwithcomments.controller;


import com.sparta.blogwithcomments.dto.CommentRequestDto;
import com.sparta.blogwithcomments.model.Comment;
import com.sparta.blogwithcomments.model.Content;
import com.sparta.blogwithcomments.repository.CommentRepository;
import com.sparta.blogwithcomments.repository.ContentRepository;
import com.sparta.blogwithcomments.security.UserDetailsImpl;
import com.sparta.blogwithcomments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentRepository commentRepository;
    private final ContentRepository contentRepository;
    private final CommentService commentService;
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

    //특정 게시물에 대한 댓글조회
    @GetMapping("/api/comment/{contentId}")
    @ResponseBody
    public List<Comment> getComments(@PathVariable Long contentId){
        return commentRepository.findAllByContentIdOrderByCreatedAtDesc(contentId);
    }

    //댓글 수정하기
    @PutMapping("/api/comment/{commentId}")
    @ResponseBody
    public Long updateProduct(@PathVariable Long commentId,
                              @RequestBody CommentRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        //내가 쓴글인지 확인
        Long writerId = commentRepository.findById(commentId).get().getUser().getId();
        if(userDetails.getUser().getId() != writerId) {
            return -1L;
        }else{
            Comment comment = commentService.updateComment(commentId,requestDto);
        }
        return commentId;
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    @ResponseBody
    public Long deleteReply(@PathVariable Long commentId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long writerId = commentRepository.findById(commentId).get().getUser().getId();
        if(userDetails.getUser().getId() != writerId){
            return -1L;
        }else{
            System.out.println("delete1");
            commentRepository.deleteById(commentId);
        }
        System.out.println("delete2");
        return commentId;
    }



}
