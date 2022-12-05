package com.example.mentomen.member.api;

import com.example.mentomen.article.dto.ArticleRequestDto;
import com.example.mentomen.article.dto.ArticleResponseDto;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final ArticleService articleService;

    @GetMapping("/api/profile/{id}")
    public String userProfile(@PathVariable Long id) {

        List<ArticleResponseDto> articles = articleService.userProfile(id);

        JsonObject obj = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for (ArticleResponseDto article : articles) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", article.getTitle());
            jsonObject.addProperty("content", article.getContent());

            jsonArray.add(jsonObject);
        }

        UserDto userDto= userService.findById(id);

        System.out.println("UserDto: "+userDto.getNickname());
        obj.addProperty("nickname", userDto.getNickname());
        obj.addProperty("discription", userDto.getDiscription());

        obj.add("articleList", jsonArray);

        return obj.toString();
    }

    @PatchMapping("/api/users")
    public void update(@Valid @RequestBody UserDto userDto, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        userService.update(principal.getUsername(),userDto);
    }

    @GetMapping("/api/users")
    public UserDto user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        return userService.findByEmail(principal.getUser().getEmail());
    }

}