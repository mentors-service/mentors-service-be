package com.example.mentomen.article.service;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.repository.ArticleRepository;
import com.example.mentomen.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> articles() {

        return articleRepository.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleResponseDto findById(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 조회 실패! 게시글이 없습니다."));

        return new ArticleResponseDto(article);
    }


    public Long save(ArticleRequestDto requestDto,String email) {

        Article article = requestDto.toEntity();
        article.confirmUser(userRepository.findByEmail(email));

        return articleRepository.save(article).getId();
    }

    public Long update(Long id, ArticleRequestDto requestDto) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 수정 실패! 게시글 댓글이 없습니다." + id));

        article.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    public void delete(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 삭제 실패! 게시글 댓글이 없습니다." + id));

        articleRepository.delete(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> myArticles(String username) {

        return articleRepository.findByUserEmail(username)
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    }
}
