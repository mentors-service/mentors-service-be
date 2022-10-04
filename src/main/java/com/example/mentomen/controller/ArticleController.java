package com.example.mentomen.controller;

import com.example.mentomen.dto.ArticleForm;
import com.example.mentomen.entity.Article;
import com.example.mentomen.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동연결!
    private ArticleRepository articleRepository;
//    @Autowired
//    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());    // println() 을 로깅으로 대체!
        // 1. Dto를 Entity 변환
        Article article = form.toEntity();
        log.info(article.toString()); // println() 을 로깅으로 대체!
        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());   // println() 을 로깅으로 대체!
        // 리다이렉트 적용: 생성 후, 브라우저가 해당 URL로 재요청
        return "redirect:/articles/" + saved.getId();
    }


}
