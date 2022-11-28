package com.example.mentomen.member.config;

import com.example.mentomen.member.config.jwt.JwtCommonAuthorizationFilter;
import com.example.mentomen.member.config.jwt.JwtTokenProvider;
import com.example.mentomen.member.config.oauth.PrincipalOauth2UserService;
import com.example.mentomen.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
// secured 어노테이션 활성화
// preAuthorize 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final CorsConfig corsConfig;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    @Value("${server.security.enabled}")
    private Boolean springSecurityEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (springSecurityEnabled) {
            http.headers().frameOptions().disable();
            http.csrf().disable(); // csrf 토큰
            http.cors();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.formLogin().disable();
            http.httpBasic().disable();
            http.addFilter(new JwtCommonAuthorizationFilter(authenticationManager(), tokenProvider, userRepository));

            http.authorizeRequests()
                    .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().permitAll()
                    .and()
                    .oauth2Login()
                    .loginPage("/login")
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService)
                    .and()
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
                            String token = tokenProvider.create(authentication);
                            // response.addHeader("Authorization", "Bearer " + token);
                            String url = makeRedirectUrl(token);
                            RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
                            redirectStrategy.sendRedirect(request, response, url);

                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                AuthenticationException exception) throws IOException, ServletException {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        }
                    });
        } else {
            http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        }

    }

    private String makeRedirectUrl(String token) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/kakao?token=" + token)
                .build().toUriString();
    }
}
