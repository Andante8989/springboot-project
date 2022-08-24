package com.lch.book.springboot.config.auth;

import com.lch.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                    .and()
                        // authorizeRequests 는 url별 관리설정 시작점. 이게 있어야만 antMatchers 사용가능
                        .authorizeRequests()
                        // 권한 관리 대상 / 등 지정된 url은 permitAll을 통해 전체열람권한을 줌
                        .antMatchers("/", "/css/**", "/images/**",
                                "js/**", "/h2-console/**").permitAll()
                        // /api/v1/** 주소를 가진 api는 USER권한을 가진 사람만 열람가능
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                         .anyRequest().authenticated()
                    .and()
                        .logout()
                        .logoutSuccessUrl("/")
                    .and()
                        // 로그인기능에 대한 설정 진입점
                        .oauth2Login()
                        // 로그인 성공 이후 사용자 정보를 가져올때의 설정담당
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService);
    }

}
