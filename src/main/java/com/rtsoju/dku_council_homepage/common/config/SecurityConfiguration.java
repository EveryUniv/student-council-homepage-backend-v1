

package com.rtsoju.dku_council_homepage.common.config;

import com.rtsoju.dku_council_homepage.common.jwt.JwtProvider;
import com.rtsoju.dku_council_homepage.domain.auth.JwtAuthenticationFilter;
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
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨.
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final CorsFilter corsFilter; // cors 정책에서 벗어남
    private final JwtProvider jwtProvider;

    @Bean //filter를 직접 구현한고 bean에 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable() // http basic 방식 사용 안함 (http basic이란? 요청 헤더 authorization에 id와 pwd를 넣어 인증하는 방법)
                .formLogin().disable()//기존에는 로그인form을 spring에서 제공해주는데 안 쓰겠다!!
                .csrf().disable()//csrf->안좋은거아님?
                .addFilter(corsFilter)//cors정책 -> 같은 도메인에서만..
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll() //추후, access등록이 필요한 부 ex)mypage
                .antMatchers("/api/sms").permitAll()
                .antMatchers("/api/email").permitAll()
                .anyRequest().hasRole("USER") //이 외는 USER권한이 있는 사람만 접근
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}