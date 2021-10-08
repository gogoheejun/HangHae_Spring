package com.sparta.blogwithcomments.service;

import com.sparta.blogwithcomments.dto.SignupRequestDto;
import com.sparta.blogwithcomments.handler.ex.CustomValidationException;
import com.sparta.blogwithcomments.model.User;
import com.sparta.blogwithcomments.repository.UserRepository;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    private String username;
    private String password;
    private String passwordCheck;

    @BeforeEach
        //모든 테스트마다 이 함수를 거치고 실행하도록함
    void setup() {
        username = "aaa";
        password = "1234";
        passwordCheck = "1234";
    }

    @Test
    @DisplayName("회원정상가입: 저장되는지")
    void test1(){
        //given
        SignupRequestDto requestDto = new SignupRequestDto(
                username,
                password,
                passwordCheck
        );

        // 가짜 userRepository릁 통해 UserService생성.
        UserService userService = new UserService(passwordEncoder, userRepository);

        //when
        String message= userService.registerUser(requestDto);

        //then
        assertEquals("registered", message);
    }

    @Nested
    @DisplayName("실패케이스")
    class FailCases{
        @Test
        @DisplayName("중복사용자 확인")
        void mustFail1(){
        //given
            SignupRequestDto requestDto = new SignupRequestDto(
                    username,
                    password,
                    passwordCheck
            );
            // 가짜 userRepository릁 통해 UserService생성.
            UserService userService = new UserService(passwordEncoder, userRepository);
            //이미 username이 중복하여 존재하는 User를 만들고
            User sameNameUser = new User(username, password);
            //리파지토리가 찾으면 방금만든 이름같은애를 찾아옴.
            when(userRepository.findByUsername(username))
                    .thenReturn(Optional.of(sameNameUser));

        //when...아니 CustomValidationException를 throw하면 걍 이거 가져가면되나
            Exception exception = assertThrows(CustomValidationException.class,()->{
                userService.registerUser(requestDto);
            });
            assertEquals("아이디 중복",exception.getMessage());
        }

        @Test
        @DisplayName("비밀번호 확인일치안하는 경우")
        void mustFail2(){

        }
    }
}
























