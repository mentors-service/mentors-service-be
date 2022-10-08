package com.example.mentomen.api;

import com.example.mentomen.dto.ArticleDto;
import com.example.mentomen.entity.Article;
import com.example.mentomen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value="/api/articles")
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    // 전체리스트
    @GetMapping("/")
    public ResponseEntity<List<Article>> index() {
        List<Article> index = articleService.index();
        return ResponseEntity.status(HttpStatus.OK).body(index);
    }

    //하나만
    @GetMapping("/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id) {

        Article showed = articleService.show(id);
        return ResponseEntity.status(HttpStatus.OK).body(showed);
    }
    // POST
    @PostMapping("/")
    public ResponseEntity<Article> create(@RequestBody ArticleDto dto) {
        Article created  = articleService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleDto dto) {
        Article updated = articleService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        Article deleted = articleService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
