package com.sparta.blogwithcomments.repository;

import com.sparta.blogwithcomments.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentIdOrderByCreatedAtDesc(Long content_id);
}
