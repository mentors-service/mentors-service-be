package com.example.mentomen.article.api;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.article.vo.ArticleVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @RequestParam(name = "searchVal") String searchVal,
    @RequestParam(name = "orderObj") String orderObj,
    @RequestParam(name = "orderBy") String orderBy
  ) {
    return ResponseEntity.ok(
      articleService.articles(
        limit,
        offset,
        searchObj,
        searchVal,
        orderObj,
        orderBy
      )
    );
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/{id}")
  public ResponseEntity<ArticleVO> getArticle(@PathVariable Integer id) {
    return ResponseEntity.ok(articleService.findById(id));
  }

  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<String> saveArticle(@RequestBody ArticleVO article) {
    Integer res = articleService.save(article);
    if (res == 1) {
      return ResponseEntity.ok().body("Success Save Article");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ArticleVO> updateArticle(
    @PathVariable Integer id,
    @RequestBody ArticleRequestDto requestDto
  ) {
    return ResponseEntity.ok(articleService.update(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    Integer res = articleService.delete(id);
    if (res == 1) {
      return ResponseEntity.ok().body("Success Delete Article");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
