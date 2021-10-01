package com.sparta.springcore.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){
        //h2-console사용에 대한 허용(CSRF, FrameOption 무시)...나중에 공부하고 일단 공식처럼 외우셈셈
       web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //회원관리처리 api(POST /user/**)에 대해 CSRF 무시
        http.csrf().ignoringAntMatchers("/user/**");

        http.authorizeRequests()
// image 폴더를 login 없이 허용
                .antMatchers("/images/**").permitAll()
// css 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()
                //회원관리 관련처리는 모두 허용할거임.
                .antMatchers("/user/**").permitAll()
// 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
// 로그인 기능 허용
                    .formLogin()
                    .loginPage("/user/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/user/login?error")
                    .permitAll()
                .and()
// 로그아웃 기능 허용
                    .logout()
                    .permitAll();
    }
}