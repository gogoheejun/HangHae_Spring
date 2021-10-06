package com.sparta.blogwithcomments.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //작성자는 익명이므로 매핑이 안됨.어떤 글에 대한 댓글이냐로 해야함
    @ManyToOne
    @JoinColumn(name="CONTENT_ID",nullable = false)
    private Content content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;

    public Comment(Content content, User user, String comment){
        this.content = content;
        this.user = user;
        this.comment = comment;
    }
}
