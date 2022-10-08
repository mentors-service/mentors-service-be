package com.example.mentomen.article.dto;

import com.example.mentomen.article.entity.Article;
import com.example.mentomen.comment.dto.CommentDto;
import com.example.mentomen.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ArticleDto {

    private Long id;

    private String title;

    private String content;

    public static ArticleDto createArticleDto(Article article) {
        return new ArticleDto(

                article.getId(),
                article.getTitle(),
                article.getContent()

        );
    }


}
