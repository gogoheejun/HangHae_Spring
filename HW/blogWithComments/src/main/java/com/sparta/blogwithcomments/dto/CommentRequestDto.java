package com.sparta.blogwithcomments.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String userId;
    private String comment;
}