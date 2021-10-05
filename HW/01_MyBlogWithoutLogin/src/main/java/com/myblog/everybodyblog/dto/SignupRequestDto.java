package com.myblog.everybodyblog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @Size(min=3) @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "영어,숫자만 가능합니다")
    private String username;
    @Size(min=4)
    private String password;
    private String passwordCheck;
}
