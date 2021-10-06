package com.sparta.blogwithcomments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogWithCommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogWithCommentsApplication.class, args);
    }

}
