package com.example.mentomen.article.service;

import com.example.mentomen.article.dto.ArticleDto;
import com.example.mentomen.comment.dto.CommentDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.repository.ArticleRepository;
import com.example.mentomen.comment.entity.Comment;
import com.example.mentomen.comment.repository.CommentRepository;
import com.example.mentomen.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<ArticleDto> articles() {

        return articleRepository.findAll()
                .stream()
                .map(article -> ArticleDto.createArticleDto(article))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Article show(Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    public ArticleDto create(ArticleDto dto) {

        //게시글 엔티티 생성
        Article article = Article.createArticle(dto);
        //게시글 엔티티 디비저장
        Article created = articleRepository.save(article);
        //엔티티 디티오로 변환하여 반환
        return ArticleDto.createArticleDto(created);
    }

    public ArticleDto update(Long id, ArticleDto dto) {
        // 게시글 조회 및 예외 발생
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 수정 실패! 게시글 댓글이 없습니다."));
        // 게시글 수정
        target.patch(dto);
        // DB로 갱신
        Article updated = articleRepository.save(target);
        // 게시글 엔티티를 DTO로 변환 및 반환
        return ArticleDto.createArticleDto(updated);
    }

    public ArticleDto delete(Long id) {

        //게시글찾기
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 삭제 실패! 대상이 없습니다."));
        //게시글의 댓글 찾기
        List<CommentDto> comments =commentRepository.findByArticleId(id)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
        //만약 댓글이 있다면 모두삭제
        if(comments !=null){
            for(CommentDto comment:comments){
                commentService.delete(comment.getId());
            }
        }
        // 게시글 삭제
        articleRepository.delete(target);
        // 삭제 게시글을 DTO로 반환
        return ArticleDto.createArticleDto(target);

    }

}
