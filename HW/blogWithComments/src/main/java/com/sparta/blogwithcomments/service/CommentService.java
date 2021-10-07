package com.sparta.blogwithcomments.service;

import com.sparta.blogwithcomments.dto.CommentRequestDto;
import com.sparta.blogwithcomments.model.Comment;
import com.sparta.blogwithcomments.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment updateComment(Long id, CommentRequestDto requestDto){
        String comment = requestDto.getComment();
        Comment CommentObj = commentRepository.findById(id)
                .orElseThrow(()->new NullPointerException("해당 댓글이 존재하지 않네요"));

        CommentObj.setComment(comment);
        commentRepository.save(CommentObj);

        return CommentObj;
    }
}
