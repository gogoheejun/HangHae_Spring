package com.sparta.blogwithcomments.repository;

import com.sparta.blogwithcomments.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByOrderByCreatedAtDesc();
}
