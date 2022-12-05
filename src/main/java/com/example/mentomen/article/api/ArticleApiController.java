package com.example.mentomen.article.api;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.member.config.auth.PrincipalDetails;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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


    //@Secured("ROLE_USER")
    @PostMapping
    public Long save(@Valid @RequestBody ArticleRequestDto requestDto,Authentication authentication) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        return articleService.save(requestDto,principal.getUsername());
    }

    //@Secured("ROLE_USER")
    @PatchMapping("/{id}")
    public Long update(@Valid@ PathVariable Long id,
                       @RequestBody ArticleRequestDto requestDto) {

        return articleService.update(id, requestDto);
    }

    //@Secured("ROLE_USER")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {

        articleService.delete(id);
        return id;
    }
}