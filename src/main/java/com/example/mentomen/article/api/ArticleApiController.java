package com.example.mentomen.article.api;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.article.vo.ArticleVO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/articles")
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<ArticleVO>> getArticleList(
            @RequestParam(name = "limit") Integer limit, // 10개
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "searchObj") String searchObj,
            @RequestParam(name = "searchVal") String searchVal) {
        return ResponseEntity.ok(articleService.articles());
    }

    // TODO

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<ArticleVO>> getCommentsList(
            @RequestParam(name = "limit") Integer limit, // 10개
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "searchObj") String searchObj,
            @RequestParam(name = "searchVal") String searchVal) {
        return ResponseEntity.ok(articleService.articles());
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ArticleVO getArticle(@PathVariable Long id) {

        return articleService.findById(id);
    }

    @PatchMapping("/{id}")
    public Long update(@Valid @PathVariable Long id,
            @RequestBody ArticleRequestDto requestDto) {

        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {

        articleService.delete(id);
        return id;
    }

}
