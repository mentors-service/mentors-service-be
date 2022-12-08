package com.example.mentomen.article.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class ArticleListVO {
    private Integer currentPage;
    private Integer totalPages;

    @Singular("article")
    private List<ArticleVO> data;
}
