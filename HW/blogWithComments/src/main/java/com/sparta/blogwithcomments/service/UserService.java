package com.sparta.blogwithcomments.service;


import com.sparta.blogwithcomments.dto.SignupRequestDto;
import com.sparta.blogwithcomments.handler.ex.CustomValidationException;
import com.sparta.blogwithcomments.model.User;
import com.sparta.blogwithcomments.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String registerUser(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        //비밀번호 확인
        Map<String,String> errorMap = new HashMap<>();
        if(!password.equals(passwordCheck)) {
            errorMap.put("password","비밀번호 체크해주세요");
            throw new CustomValidationException("유효성 검사실패", errorMap);
        }
        //중복사용자 확인
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            errorMap.put("username","중복된 닉네임입니다.");
            throw new CustomValidationException("아이디 중복",errorMap);
        }

        //패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);
        return "registered";
    }
}
