package com.example.mentomen.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String content;

    @Builder
    public ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
