package com.myblog.everybodyblog.controller;

import com.myblog.everybodyblog.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails != null){
            model.addAttribute("username",userDetails.getUsername());
        }else{
            model.addAttribute("username","익명");
        }

        return "index";
    }
}
