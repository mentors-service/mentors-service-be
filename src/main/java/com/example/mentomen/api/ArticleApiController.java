package com.example.mentomen.api;

import com.example.mentomen.dto.ArticleForm;
import com.example.mentomen.dto.CommentDto;
import com.example.mentomen.entity.Article;
import com.example.mentomen.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    // 전체리스트
    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> index() {
        List<Article> index = articleService.index();
        return ResponseEntity.status(HttpStatus.OK).body(index);
    }

    //하나만
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id) {

        Article showed = articleService.show(id);
        return (showed != null) ?
                ResponseEntity.status(HttpStatus.OK).body(showed) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created  = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }

}
