package com.example.mentomen.article.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.mentomen.article.dto.SocialDto;
import com.example.mentomen.article.entity.UserEntity;
import com.example.mentomen.article.repository.UserRepository;
import com.example.mentomen.article.service.KakaoService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    public String  KaKaoCallback(String code, HttpServletResponse response){

        SocialDto socialDto = kakaoService.KaKaoCallback2(code);

        UserEntity user = userRepository.findByEmail(socialDto.getEmail()).orElse(null);
        if (user == null){
            // 회원가입
            // password: random UUID
            String password = UUID.randomUUID().toString();
            // String encodedPassword = passwordEncoder.encode(password);
            user=socialDto.toEntity(password);
            userRepository.save(user);
        }
//        // JWT 토큰 생성
//        String token = JWT.create()
//                .withSubject("cos토큰")
//                .withExpiresAt(new Date(System.currentTimeMillis() + 60000*10))
//                .withClaim("email", socialDto.getEmail())
//                .sign(Algorithm.HMAC512("cos"));
//         //JWT 토큰 헤더에 담아 전달
//        response.addHeader(env.getProperty("token.header"), env.getProperty("token.prefix") + token);

       // System.out.println("response: "+response);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setExpiration(new Date(System.currentTimeMillis() + 60000*10)) // (4)
                .claim("email", socialDto.getEmail()) // (5)
                .signWith(SignatureAlgorithm.HS256, "secret") // (6)
                .compact();
        //return new ResponseEntity(HttpStatus.OK);
    }

}
