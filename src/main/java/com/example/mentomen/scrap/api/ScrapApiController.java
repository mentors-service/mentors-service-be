package com.example.mentomen.scrap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.scrap.service.ScrapService;

@RestController
@RequestMapping(value = "/api/scrap")
public class ScrapApiController {
    @Autowired
    private ScrapService scrapService;

    @CrossOrigin(origins = "*")
    @PatchMapping("/{articleId}")
    public ResponseEntity<String> updateScrap(
            @PathVariable Integer articleId,
            @RequestParam(name = "userId", required = true) Integer userId) {
        return ResponseEntity.ok(scrapService.updateScrapStatus(articleId, userId));
    }

}
