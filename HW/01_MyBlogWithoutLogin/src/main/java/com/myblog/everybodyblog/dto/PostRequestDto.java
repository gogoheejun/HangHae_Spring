package com.myblog.everybodyblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class PostRequestDto {
    private String username;
    private String title;
    private String contents;
}
