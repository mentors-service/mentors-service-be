package com.example.mentomen.scrap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.scrap.service.ScrapService;
import com.example.mentomen.scrap.vo.ScrapVO;

@RestController
@RequestMapping(value = "/api/scrap")
public class ScrapApiController {
    @Autowired
    private ScrapService scrapService;

    @PatchMapping("/{articleId}")
    public ResponseEntity<ScrapVO> updateArticle(
            @PathVariable Integer articleId,
            @RequestBody ScrapVO scrapVO) {
        return ResponseEntity.ok(scrapService.updateScrapStatus(articleId, scrapVO));
    }

}
