package com.example.mentomen.article.service;

import com.example.mentomen.article.code.ArticleStatusCode;
import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.repository.ArticleRepository;
import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.article.vo.CommentVO;
import com.example.mentomen.article.vo.CreaterVO;
import com.example.mentomen.article.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Value("${mentomen.api.test}")
    private Boolean isTest;

    @Transactional(readOnly = true)
    public List<ArticleVO> articles() {
        if (isTest) {
            List<ArticleVO> testRes = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                testRes.add(testArticle());
            }
            return testRes;
        } else
            return new ArrayList<>();
        // return articleRepository.findAll()
        // .stream()
        // .map(article -> new ArticleResponseDto(article))
        // .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ArticleVO findById(Long id) {
        if (isTest) {
            return testArticle();
        } else {
            // Article article = articleRepository.findById(id)
            // .orElseThrow(() -> new IllegalArgumentException("게시글 조회 실패! 게시글이 없습니다."));

            // return new ArticleResponseDto(article);
            return ArticleVO.builder().build();
        }
    }

    public Long update(Long id, ArticleRequestDto requestDto) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 수정 실패! 게시글 댓글이 없습니다." + id));

        return id;
    }

    public void delete(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 삭제 실패! 게시글 댓글이 없습니다." + id));

        articleRepository.delete(article);
    }

    private ArticleVO testArticle() {
        ArticleVO article = ArticleVO.builder().build();
        // article.setContents(
        // "this is test Contents~!this is test Contents~!this is test Contents~!this is
        // test Contents~!this is test Contents~!");
        // article.setTitle("test title");
        // article.setPlace("Seoul");
        // article.setCreatedAt(new Date());
        // CreaterVO creater = new CreaterVO();
        // creater.setId("asdi-kjnof-sdfvwse12123");
        // creater.setImg("is not used");
        // creater.setName("kyounghwan");
        // article.setCreater(creater);
        // MemberVO member = new MemberVO();
        // member.setJoinCnt(1);
        // member.setTotalCnt(10);
        // article.setMember(member);
        // List<CommentVO> childComments = new ArrayList<>();
        // List<CommentVO> comments = new ArrayList<>();
        // CommentVO com1 = new CommentVO();
        // com1.setCreater(creater);
        // com1.setContent("this is parents comments");
        // com1.setCreatedAt(new Date());
        // CommentVO com2 = new CommentVO();
        // com2.setCreater(creater);
        // com2.setContent("this is parents comments");
        // com2.setCreatedAt(new Date());
        // childComments.add(com2);
        // com1.setChilds(childComments);
        // comments.add(com1);
        // article.setComments(comments);
        // article.setStatus(ArticleStatusCode.ARTICLE_INPROGRESS);
        return article;
    }
}
