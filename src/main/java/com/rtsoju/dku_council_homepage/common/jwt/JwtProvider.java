package com.rtsoju.dku_council_homepage.common.jwt;

import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("temp")
    private String secretKey = "temp";

    private Long phoneValidMillisecond = 3 * 60 * 1000L; // 3 hour

    private Long accessTokenValidMillisecond = 60 * 60 * 1000L; // 1 hour
    private Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String code) {
        Claims claims = Jwts.claims();
        claims.put("code", code);

        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + phoneValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean phoneValidate(String token, String code) {
        validationToken(token);
        Claims claims = parseClaims(token);

        if (claims.get("code").equals(code)) {
            return true;
        }
        return false;
    }


    // jwt의 유효성 및 만료일자 확인
    public boolean validationToken(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
//           return !claimsJws.getBody().getExpiration().before(new Date()); // 만료 날짜가 현재보다 이전이면 False
        }catch(JwtException | IllegalArgumentException e){
            log.error(e.toString());
            return false;
        }
    }


    // Jwt 토큰 복호화해서 가져오기
    private Claims parseClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
