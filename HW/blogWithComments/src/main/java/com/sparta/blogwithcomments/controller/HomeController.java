package com.sparta.blogwithcomments.controller;


import com.sparta.blogwithcomments.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("작동했나");
        if(userDetails != null){
            model.addAttribute("username",userDetails.getUsername());
        }else{
            model.addAttribute("username","익명");
        }

        return "index";
    }
}
