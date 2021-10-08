package com.sparta.blogwithcomments.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.blogwithcomments.dto.SignupRequestDto;
import com.sparta.blogwithcomments.handler.ex.CustomValidationException;
import com.sparta.blogwithcomments.security.UserDetailsImpl;
import com.sparta.blogwithcomments.service.KakaoUserService;
import com.sparta.blogwithcomments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    //회원가입페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("signup:"+userDetails);
        if(userDetails != null){
            model.addAttribute("entry","already-logged-in");
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }
        return "signup";
    }


    //회원가입 요청처리
    @PostMapping( "/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사실패", errorMap);
        }else{
            userService.registerUser(requestDto);
            System.out.println("회원가입성공");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/user/login")
    public String login(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){

        return "login";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }
}
