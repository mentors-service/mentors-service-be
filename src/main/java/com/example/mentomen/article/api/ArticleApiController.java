package com.example.mentomen.article.api;

import com.example.mentomen.article.dto.ArticleDto;
import com.example.mentomen.article.entity.Article;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//@RequestMapping(value="/api")
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/articles")
public class ArticleApiController {
    private final ArticleService articleService;

    // 전체리스트
    @GetMapping
    public ResponseEntity<List<ArticleDto>> articles() {

        List<ArticleDto> dtos = articleService.articles();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }

    //하나만
    @GetMapping("/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id) {

        Article showed = articleService.show(id);
        return ResponseEntity.status(HttpStatus.OK).body(showed);

    }
    // POST
    @PostMapping
    public ResponseEntity<ArticleDto> create(@Valid @RequestBody ArticleDto dto) {

        ArticleDto createdDto  = articleService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);

    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id,
                                          @RequestBody ArticleDto dto) {

        ArticleDto updatedDto = articleService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long id) {

        ArticleDto deletedDto = articleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
