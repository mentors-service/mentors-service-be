package com.example.mentomen.article.api;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDto> articles() {

        return articleService.articles();
    }

    @GetMapping("/{id}")
    public ArticleResponseDto findById(@PathVariable Long id) {

        return articleService.findById(id);
    }

    @PostMapping
    public Long save(@Valid @RequestBody ArticleRequestDto requestDto) {

       // ResponseEntity.status(HttpStatus.OK).body(showed);
        return articleService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public Long update(@Valid @PathVariable Long id,
                       @RequestBody ArticleRequestDto requestDto) {

        return articleService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        articleService.delete(id);
        //return id;
    }
}
