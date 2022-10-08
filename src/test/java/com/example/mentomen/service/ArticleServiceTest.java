package com.example.mentomen.service;

import com.example.mentomen.article.dto.ArticleDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String title = "가가가가";
        String content = "1111";
        ArticleDto dto = new ArticleDto(null, title, content);
        Article expected = new Article(1L, title, content);
        // 실제
        ArticleDto articleDto = articleService.create(dto);
        // 비교
        assertEquals(expected.getTitle(), dto.getTitle());
        assertEquals(expected.getContent(), dto.getContent());
    }


    @Test
    @DisplayName("게시글 수정")
    void update() {
        // 예상
        String title = "가가가가";
        String content = "1111";
        ArticleDto dto = new ArticleDto(null, title, content);
        ArticleDto dto2 = new ArticleDto(null, "수정됨", "2222");
        ArticleDto articleDto = articleService.create(dto);
        ArticleDto articleDto2 = articleService.create(dto2);
        // 실제
        ArticleDto expected=articleService.update(1L,dto2);
        // 비교
        assertEquals(expected.getTitle(), dto2.getTitle());
        assertEquals(expected.getContent(), dto2.getContent());
    }

    @Test
    @DisplayName("게시글 수정")
    void delete() {
        // 예상
        String title = "가가가가";
        String content = "1111";
        ArticleDto dto = new ArticleDto(null, title, content);
        ArticleDto dto2 = new ArticleDto(null, "나나나나", "2222");
        ArticleDto articleDto = articleService.create(dto);
        ArticleDto articleDto2 = articleService.create(dto2);
        // 실제
        ArticleDto deleted =articleService.delete(1L);
        // 비교
        assertEquals(deleted.toString(),articleDto.toString());
    }
}