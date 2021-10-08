package com.sparta.blogwithcomments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor //테스트코드를 위해 만듦
public class SignupRequestDto {
    @Size(min=3) @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "영어,숫자만 가능합니다")
    private String username;
    @Size(min=4)
    private String password;
    private String passwordCheck;
}
