package com.example.mentomen.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class KakaoProfile {

    public Long id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;
    @JsonIgnoreProperties(ignoreUnknown=true)
    @Data
    public class Properties {

        public String nickname;
        public String profile_image;
        public String thumbnail_image;

    }
    @JsonIgnoreProperties(ignoreUnknown=true)
    @Data
    public class KakaoAccount {

        public Boolean profile_nickname_needs_agreement;
        public Boolean profile_image_needs_agreement;
        public Profile profile;
        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;

        @Data
        @JsonIgnoreProperties(ignoreUnknown=true)
        public class Profile {

            public String nickname;
            public String thumbnail_imageUrl;
            public String profile_image_url;
            public Boolean is_default_image;

        }
    }
}

