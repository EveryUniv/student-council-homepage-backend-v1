

package com.rtsoju.dku_council_homepage.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.JwtAuthenticationFilter;
import com.rtsoju.dku_council_homepage.domain.auth.filterException.FilterExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import java.net.http.HttpRequest;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final CorsFilter corsFilter; // cors 정책에서 벗어남
    private final JwtProvider jwtProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable() // http basic 방식 사용 안함 (http basic이란? 요청 헤더 authorization에 id와 pwd를 넣어 인증하는 방법)
                .formLogin().disable()
                .csrf().disable()
                .addFilter(corsFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/reissue").permitAll()
                .antMatchers("/api/sms").permitAll()
                .antMatchers("/api/email").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                // 자동 주입으로 완성? OR new 생성자로 등록? 뭐가 좋을까...
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new FilterExceptionHandler(new ObjectMapper()), JwtAuthenticationFilter.class)
                .build();
    }


}