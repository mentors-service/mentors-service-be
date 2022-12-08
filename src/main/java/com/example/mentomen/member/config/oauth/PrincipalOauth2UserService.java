package com.example.mentomen.member.config.oauth;

import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.member.entity.UserEntity;
import com.example.mentomen.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회
        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            oAuth2UserInfo = new KakaoUserInfo((Map) oAuth2User.getAttributes());
        } else {
            System.out.println("카카오만 지원함");
        }
        Optional<UserEntity> userOptional = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(),
                oAuth2UserInfo.getProviderId());

        UserEntity user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            // user가 존재하면 update 해주기
            user.setEmail(oAuth2UserInfo.getEmail());
            userRepository.save(user);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            user = UserEntity.builder()
                    .username(oAuth2UserInfo.getName())
                    .email(oAuth2UserInfo.getEmail())
                    .role("ROLE_USER")
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
            userRepository.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }

}
