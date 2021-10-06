package com.sparta.blogwithcomments.controller;

import com.sparta.blogwithcomments.dto.CommentRequestDto;
import com.sparta.blogwithcomments.model.Comment;
import com.sparta.blogwithcomments.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

//   댓글작성 처리
    @PostMapping("api/comment")
    public void addComment(
            @RequestParam Long contentId,
            @RequestParam String comment
//            @AuthenticationPrincipal UserDetailsImpl userDetails
        ){
        System.out.println("넘어옴?"+contentId+comment);
//        if(userDetails != null){
//            //로그인한 사용자만 받아줌
//            //1.글 받기
//            System.out.println("넘어옴?"+contentId);
//            //2.사용자 id랑 합쳐서 저장
//        }else{
//            //로그인 안한사람이 쓸경우 막기
//            System.out.println("넘어옴?"+contentId);
//        }
//        return
    }

    //댓글불러오기
}
