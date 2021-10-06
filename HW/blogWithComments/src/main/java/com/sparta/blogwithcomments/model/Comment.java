package com.sparta.blogwithcomments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //작성자는 익명이므로 매핑이 안됨.어떤 글에 대한 댓글이냐로 해야함
    @Column(nullable = false)
    private String contentId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String comment;
}
