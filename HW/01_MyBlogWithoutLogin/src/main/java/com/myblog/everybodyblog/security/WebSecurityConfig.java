package com.myblog.everybodyblog.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
// 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
// 로그인 기능 허용
                .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()//모든페이지에 authenticated걸려있으면 로그인이랑 로그아웃도 못하니까 저 두개는 permit을 걸어놓음
                .and()
// 로그아웃 기능 허용
                .logout()
                .permitAll();//모든페이지에 authenticated걸려있으면 로그인이랑 로그아웃도 못하니까 저 두개는 permit을 걸어놓음
    }
}