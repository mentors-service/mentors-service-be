package com.example.mentomen.comment.api;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.example.mentomen.member.config.auth.PrincipalDetails;

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
      Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    return ResponseEntity.ok(commentService.comments(articleId, principal.getUser().getId()));
  }

  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<String> saveComment(
      @RequestParam(name = "articleId", required = true) Integer articleId,
      @RequestParam(name = "parentId", required = false) Integer parentId,
      @RequestBody CommentRetriveVO comment,
      Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = commentService.save(articleId, parentId, comment, principal.getUser().getId());
    if (res >= 1) {
      return ResponseEntity.ok().body("Success Save Comment");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PatchMapping("/{commentId}")
  public ResponseEntity<String> updateArticle(
      @PathVariable Integer commentId,
      @RequestBody CommentRetriveVO comment,
      Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = commentService.update(commentId, null, comment, principal.getUser().getId());
    if (res >= 1) {
      return ResponseEntity.ok().body("Success Update Comment");
    } else if (res == 0) {
      return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("not update");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> delete(@PathVariable Integer commentId, Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Integer res = commentService.delete(commentId, principal.getUser().getId());
    if (res == 1) {
      return ResponseEntity.ok().body("Success Delete Comment");
    } else if (res == 0) {
      return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Not Found");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
