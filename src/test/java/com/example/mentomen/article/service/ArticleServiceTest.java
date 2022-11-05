package com.example.mentomen.article.service;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    @DisplayName("게시글 등록 -> 제목 내용 다있을때")
    void create() {
        // 예상
        ArticleRequestDto dto = new ArticleRequestDto("1111", "1111");
        ArticleRequestDto dto2 = new ArticleRequestDto("2222", "2222");
        // 실행
        // 실제
        // 비교
    }

    @Test
    @DisplayName("게시글 수정")
    void update() {
        // 예상
        ArticleRequestDto dto = new ArticleRequestDto("1111", "1111");
        ArticleRequestDto dto2 = new ArticleRequestDto("수정됨", "2222");
        Long expected = articleService.update(1L, dto2);
        // 비교
        assertEquals(articleService.findById(expected).getTitle(), dto2.getTitle());
        // assertEquals(articleService.findById(expected).getContent(),
        // dto2.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void delete() {
        // 예상
        ArticleRequestDto dto = new ArticleRequestDto("1111", "1111");
        ArticleRequestDto dto2 = new ArticleRequestDto("수정됨", "2222");
        // 실제
        articleService.delete(1L);
        // 비교
        assertEquals(dto.getTitle(), dto2.getTitle());
    }
}