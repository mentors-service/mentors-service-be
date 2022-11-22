package com.example.mentomen.recruit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.recruit.service.RecruitService;

@RestController
@RequestMapping(value = "/api/recruit")
public class RecruitApiController {

    @Autowired
    private RecruitService recruitService;

    @CrossOrigin(origins = "*")
    @PatchMapping("/{articleId}")
    public ResponseEntity<String> updateRecruit(
            @PathVariable Integer articleId,
            @RequestParam(name = "userId", required = true) Integer userId) {
        return ResponseEntity.ok(recruitService.updateRecruitStatus(articleId, userId));
    }
}
