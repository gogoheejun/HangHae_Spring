package com.sparta.blogwithcomments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity @NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private Long kakaoId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Long kakaoId){
        this.username = username;
        this.password = password;
        this.kakaoId = kakaoId;
    }
}
