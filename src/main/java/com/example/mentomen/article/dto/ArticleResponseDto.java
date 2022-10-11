package com.example.mentomen.article.dto;

import com.example.mentomen.article.entity.Article;
import lombok.*;

@Getter
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;

    public ArticleResponseDto(Article article) {
        this.id=article.getId();
        this.title=article.getTitle();
        this.content=article.getContent();
    }
}
