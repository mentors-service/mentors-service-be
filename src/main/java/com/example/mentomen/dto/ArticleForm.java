package com.example.mentomen.dto;

import com.example.mentomen.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
public class ArticleForm {

    private String title;
    private String content;

    public Article toEntity() {
        return new Article( title, content);
    }
}
