package com.example.mentomen.comment.api;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.comment.service.CommentService;
import com.example.mentomen.comment.vo.CommentRetriveVO;
import com.example.mentomen.comment.vo.CommentVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/comment")
public class CommentApiController {

  @Autowired
  private CommentService commentService;

  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<List<CommentVO>> getCommentList(
      @RequestParam(name = "articleId", required = false) Integer articleId,
      @RequestParam(name = "userId", required = false) Long userId) {
    return ResponseEntity.ok(commentService.comments(articleId, userId));
  }

  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<String> saveComment(
      @RequestParam(name = "articleId", required = true) Integer articleId,
      @RequestParam(name = "parentId", required = false) Integer parentId,
      @RequestBody CommentRetriveVO comment) {
    Integer res = commentService.save(articleId, parentId, comment);
    if (res >= 1) {
      return ResponseEntity.ok().body("Success Save Comment");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/{commentId}")
  public ResponseEntity<String> updateArticle(
      @PathVariable Integer commentId,
      @RequestBody CommentRetriveVO comment) {
    Integer res = commentService.update(commentId, null, comment);
    if (res >= 1) {
      return ResponseEntity.ok().body("Success Update Comment");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    Integer res = commentService.delete(id);
    if (res == 1) {
      return ResponseEntity.ok().body("Success Delete Comment");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
