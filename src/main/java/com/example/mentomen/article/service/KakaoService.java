package com.example.mentomen.article.service;

import com.example.mentomen.article.dto.SocialDto;
import com.example.mentomen.article.entity.KakaoProfile;
import com.example.mentomen.article.entity.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
@RequiredArgsConstructor
public class KakaoService {

    public SocialDto KaKaoCallback2(String code){

        //post방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate rt= new RestTemplate();

        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String,String>params=new LinkedMultiValueMap<>();

        params.add("grant_type","authorization_code");
        params.add("client_id","85cca9d775656c6b09e19d7cb83bd145");
        params.add("redirect_uri","http://localhost:8080/member/kakao/callback");
        //변수화 시켜서 사용하는게 좋음
        params.add("code",code);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest=
                new HttpEntity<>(params,headers);

        //Http 요청하기 => post방식으로
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //ObjectMapper
        ObjectMapper objectMapper =new ObjectMapper();

        OAuthToken oauthToken=null;
        try{
            oauthToken =objectMapper.readValue(response.getBody(),OAuthToken.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }


        RestTemplate rt2= new RestTemplate();
        HttpHeaders headers2= new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String,String>> kakaoProfileTokenRequest2=
                new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileTokenRequest2,
                String.class
        );

        System.out.println("response2.getBody(): "+response2.getBody());

        //ObjectMapper
        ObjectMapper objectMapper2 =new ObjectMapper();

        KakaoProfile kakaoProfile=null;
        try{
            kakaoProfile =objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }

        //user오브젝트: username,password, email
        System.out.println("id: "+kakaoProfile.getId());
        System.out.println("이메일: "+kakaoProfile.getKakao_account().getEmail());
        System.out.println("id: "+kakaoProfile.getKakao_account().getEmail());



        SocialDto socialDto = new SocialDto(kakaoProfile.getKakao_account().getEmail(),
                kakaoProfile.getKakao_account().getProfile().getNickname(),
                kakaoProfile.getKakao_account().getProfile().getProfile_image_url());



        return socialDto;
    }




}
