package com.example.mentomen.member.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtCommonAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public JwtCommonAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                        UserRepository userRepository) {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String path = (request).getRequestURI();
        String method = (request).getMethod();
        if ((path.contains("/articles") && method.equals("GET"))) {
            chain.doFilter(request, response);
        } else {
            try {
                String token = request.getHeader("Authorization")
                        .replace("Bearer ", "");
                String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                        .getClaim("email").asString();
            } catch (NullPointerException e) {
                log.info("nullPoint");
                throw new JwtException("토큰 null");
            } catch (SignatureVerificationException e) {
                log.info("Invalid JWT signature.");
                throw new JwtException("잘못된 JWT 시그니처");
            } catch (JWTDecodeException e) {
                log.info("Invalid JWT token.");
                throw new JwtException("유효하지 않은 JWT 토큰");
            } catch (ExpiredJwtException e) {
                log.info("Expired JWT token.");
                throw new JwtException("토큰 기한 만료");
            } catch (IllegalArgumentException e) {
                log.info("JWT token compact of handler are invalid.");
                throw new JwtException("JWT token compact of handler are invalid.");
            }

            Authentication authentication = getUsernamePasswordAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }

    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization")
                .replace("Bearer ", "");
        if (token != null) {

            String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                    .getClaim("email").asString();

            if (email != null) {
                UserEntity user = userRepository.findByEmail(email);

                PrincipalDetails principalDetails = new PrincipalDetails(user);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principalDetails,
                        null,
                        principalDetails.getAuthorities());
                // 강제로 시큐리티의 세션에 접근해 Authentication객체를 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return authentication;
            }
        }
        return null;
    }
}