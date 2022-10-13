package com.example.mentomen.article.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.mentomen.article.dto.SocialDto;
import com.example.mentomen.article.entity.KakaoProfile;
import com.example.mentomen.article.entity.OAuthToken;
import com.example.mentomen.article.entity.UserEntity;
import com.example.mentomen.article.repository.UserRepository;
import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.article.service.KakaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/kakao")
public class UserController {

    private final KakaoService kakaoService;
    private final UserRepository userRepository;
    private final Environment env;
    @GetMapping("/callback")
    @ResponseBody
    public ResponseEntity KaKaoCallback(String code, HttpServletResponse response){

        SocialDto socialDto = kakaoService.KaKaoCallback2(code);

        Optional<UserEntity> user = userRepository.findByEmail(socialDto.getEmail());

        if (user.isEmpty()){
            userRepository.save(socialDto.toEntity());
        }
        // JWT 토큰 생성
        String token = JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
                .withClaim("email", socialDto.getEmail())
                .sign(Algorithm.HMAC512(env.getProperty("token.secret")));

        // JWT 토큰 헤더에 담아 전달
        response.addHeader(env.getProperty("token.header"), env.getProperty("token.prefix") + token);


        return new ResponseEntity(HttpStatus.OK);
    }

}
