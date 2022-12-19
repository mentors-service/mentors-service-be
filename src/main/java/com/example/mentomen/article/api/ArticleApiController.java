package com.example.mentomen.article.api;

import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.article.vo.ArticleRetriveVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.member.config.auth.PrincipalDetails;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/articles")
public class ArticleApiController {

  @Autowired
  private ArticleService articleService;

  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<ArticleListVO> getArticleList(
      @RequestParam(name = "offset") Integer offset,
      @RequestParam(name = "searchObj", required = false) String searchObj,
      @RequestParam(name = "searchVal", required = false) String searchVal,
      Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    return ResponseEntity.ok(
        articleService.articlesByUserId(
            offset,
            searchObj,
            searchVal, principal.getUser().getId()));
  }

  @CrossOrigin(origins = "*")

  @GetMapping("/list")
  public ResponseEntity<ArticleListVO> getArticleListNoAuth(
      @RequestParam(name = "offset") Integer offset,
      @RequestParam(name = "searchObj", required = false) String searchObj,
      @RequestParam(name = "searchVal", required = false) String searchVal) {
    return ResponseEntity.ok(
        articleService.articles(
            offset,
            searchObj,
            searchVal, null));
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/{id}")
  public ResponseEntity<ArticleVO> getArticle(@PathVariable Integer id, Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    return ResponseEntity.ok(articleService.findById(id, principal.getUser().getId()));
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/detail/{id}")
  public ResponseEntity<ArticleVO> getArticleNoAuth(@PathVariable Integer id) {
    return ResponseEntity.ok(articleService.findById(id, null));
  }

  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<String> saveArticle(@RequestBody ArticleRetriveVO article, Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = articleService.save(article, principal.getUser().getId());
    if (res == 1) {
      return ResponseEntity.ok().body("Success Save Article");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> updateArticle(
      @PathVariable Integer id,
      @RequestBody ArticleRetriveVO article, Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = articleService.update(id, article, principal.getUser().getId());
    if (res == 1) {
      return ResponseEntity.ok().body("Success Update Article");
    } else if (res == 0) {
      return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("not update");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Integer id, Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = articleService.delete(id, principal.getUser().getId());
    if (res == 1) {
      return ResponseEntity.ok().body("Success Delete Article");
    } else if (res == 0) {
      return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Not Found");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
