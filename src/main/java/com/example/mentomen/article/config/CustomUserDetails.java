//package com.example.mentomen.article.config;
//
//
//import com.example.mentomen.article.entity.UserEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class CustomUserDetails implements UserDetails{
//    private UserEntity userEntity;
//
//    public UserEntity getUser() {
//        return userEntity;
//    }
//
//    @Override
//    public String getUsername() {
//        return userEntity.getEmail();
//    }
//
//
//    @Override
//    public String getPassword() {
//        return userEntity.getPassword();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        userEntity.getRoleList().forEach(r -> {
//            authorities.add(()->{ return r;});
//        });
//        return authorities;
//    }
//
//    public CustomUserDetails(UserEntity userEntity) {
//        this.userEntity=userEntity;
//    }
//
//
//}
