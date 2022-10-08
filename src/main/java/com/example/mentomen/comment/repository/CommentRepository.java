package com.example.mentomen.comment.repository;

import com.example.mentomen.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(@Param("articleId") Long articleId);
}
