package com.example.mentomen.service;

import com.example.mentomen.dto.ArticleDto;
import com.example.mentomen.dto.CommentDto;
import com.example.mentomen.entity.Article;
import com.example.mentomen.repository.ArticleRepository;
import com.example.mentomen.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @Transactional(readOnly = true)
    public List<Article> index() {

        return articleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Article show(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleDto dto) {

        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    public Article update(Long id, ArticleDto dto) {
        // 1: DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {

        List<CommentDto> comments =commentRepository.findByArticleId(id)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리 (대상이 없는경우)
        if (target == null) {
            return null;
        }
        if(comments !=null){
            for(CommentDto comment:comments){
                commentService.delete(comment.getId());
            }
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;

    }
}
