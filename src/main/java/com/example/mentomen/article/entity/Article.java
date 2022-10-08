package com.example.mentomen.article.entity;

import com.example.mentomen.article.dto.ArticleDto;
import com.example.mentomen.comment.dto.CommentDto;
import com.example.mentomen.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {


    @Id
    @Column(name="article_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성 전략
    private Long id;
    @Column
    private String title;

    @Column
    private String content;

    public static Article createArticle(ArticleDto dto) {
        // 예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("개시글 생성 실패! 개시글의 id가 없어야 합니다.");
        // 엔티티 생성 및 반환
        return new Article(
                dto.getId(),
                dto.getTitle(),
                dto.getContent()
        );
    }

    public void patch(ArticleDto dto) {
        if (dto.getTitle() != null)
            this.title = dto.getTitle();
        if (dto.getContent() != null)
            this.content = dto.getContent();
    }


}
