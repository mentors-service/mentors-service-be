package com.example.mentomen.article.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
public class ArticleListVO {
    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;

    @Singular("article")
    private List<ArticleVO> data;
}
