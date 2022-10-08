package com.example.mentomen.article.repository;

import com.example.mentomen.article.dto.ArticleDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;

    @Test
    @DisplayName("게시글 모두 조회")
    @Transactional
    void findById() {
        /* Case 1: 모두 조회 */
        {

            //데이터 넣기
            ArticleDto dto1 = new ArticleDto(null, "가가가가", "1111");
            ArticleDto dto2 = new ArticleDto(null, "나나나나", "2222");
            ArticleDto articleDto = articleService.create(dto1);
            ArticleDto articleDto2 = articleService.create(dto2);
            // 준비
            List<Article> expected = articleRepository.findAll();

            Article a = new Article(1L, "가가가가", "1111");
            Article b = new Article(2L, "나나나나", "2222");

            List<Article> articles = Arrays.asList(a, b);
            // 검증
            assertEquals(expected.toString(), articles.toString(), "모든 게시글 출력!");
        }

        /* Case 3: 9번게시물 댓글 조회해보기*/
        /* Case 4: 999번게시물 댓글 조회해보기*/
        /* Case 5: -1번게시물 댓글 조회해보기*/



    }
}