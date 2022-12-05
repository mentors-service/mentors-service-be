package com.example.mentomen.article.repository;

import com.example.mentomen.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a join fetch a.user where a.user.email = email")
    List<Article> findByUserEmail(@Param("email") String email);

    @Query("select a from Article a join fetch a.user where a.user.id = :userId")
    List<Article> findByUserId(@Param("userId") Long userId);
}
