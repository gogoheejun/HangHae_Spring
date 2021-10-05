package com.myblog.everybodyblog.service;

import com.myblog.everybodyblog.dto.SignupRequestDto;
import com.myblog.everybodyblog.model.User;
import com.myblog.everybodyblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //비밀번호 확인
        //1. - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
        //2. 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
        //3. 확인비밀번호 일치

        //중복사용자 확인
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
    }
}
