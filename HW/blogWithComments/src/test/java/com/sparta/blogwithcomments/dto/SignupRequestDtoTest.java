package com.sparta.blogwithcomments.dto;

import com.sparta.blogwithcomments.handler.ex.CustomValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;
//닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 이루어져 있어야 합니다
class SignupRequestDtoTest {

    @Nested
    @DisplayName("회원가입하기 위한 Dto객체 생성")
    class CreateSignupRequestDto{
        private String username;
        private String password;
        private String passwordCheck;

        private Validator validator;

        @BeforeEach
        void setup(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();

            username = "aaa";
            password = "1234";
            passwordCheck = "1234";
        }

        @Test
        @DisplayName("정상케이스")
        void createDto_Normal(){
            //givne
                //beforeEach로 생략
            //when
            SignupRequestDto requestDto = new SignupRequestDto(
                    username,
                    password,
                    passwordCheck
            );
//            Set<ConstraintValidation>
            //then
            assertEquals(username , requestDto.getUsername());
            assertEquals(password , requestDto.getPassword());
            assertEquals(passwordCheck , requestDto.getPasswordCheck());
        }

        @Nested
        @DisplayName("실패케이스")
        class FailCases{
            @Test
            @DisplayName("닉네임 최소 3자 이하")
            void fail1(){
                //given
                username = "aa###";

                //when-then
                Exception exception = assertThrows(CustomValidationException.class,()->{
                    new SignupRequestDto(username,password,passwordCheck);
                });

                //then
                assertEquals(CustomValidationException.class, exception );
            }
        }
    }
}






















