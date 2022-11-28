package com.example.mentomen.scrap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.scrap.service.ScrapService;

@RestController
@RequestMapping(value = "/api/scrap")
public class ScrapApiController {
    @Autowired
    private ScrapService scrapService;

    @CrossOrigin(origins = "*")
    @PatchMapping
    public ResponseEntity<String> updateScrap(
            @RequestParam(name = "articleId", required = true) Integer articleId,
            Authentication authentication) {
        try {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            return ResponseEntity.ok(scrapService.updateScrapStatus(articleId, principal.getUser().getId()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body("Required Auth Token");
        }
    }

}
