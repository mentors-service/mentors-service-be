package com.example.mentomen.comment.dto;

import com.example.mentomen.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;

    @JsonProperty("article_id") //-> 제이슨에서 얘는 다르게 날라옴
    private Long articleId;

    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(

                comment.getId(),
                comment.getArticle().getId(),
                comment.getBody()

        );
    }
}
