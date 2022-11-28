package com.example.mentomen.member.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserApiController {
    private final UserService userService;
    private final ArticleService articleService;

    @GetMapping
    public UserDto myArticles(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = userService.findByEmail(principal.getUser().getEmail());
        return userDto;
    }

    @PatchMapping
    public void update(@Valid @RequestBody UserDto userDto, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        userService.update(principal.getUser().getEmail(), userDto);
    }

}
