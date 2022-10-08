package com.example.mentomen.dto;

import com.example.mentomen.entity.Article;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class ArticleDto {

    private String title;
    private String content;

    public Article toEntity() {
        return new Article( title, content);
    }
}
