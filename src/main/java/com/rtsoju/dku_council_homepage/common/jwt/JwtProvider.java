package com.rtsoju.dku_council_homepage.common.jwt;

import io.jsonwebtoken.*;
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

    @Value("${auth.sms.expirationSeconds}")
    private int expirationSeconds;

    private Long accessTokenValidMillisecond = 60 * 60 * 1000L; // 1 hour
    private Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private String createToken(Claims claims, long expiredDuration) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredDuration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createSMSAuthToken(String phone, String code) {
        Claims claims = Jwts.claims();
        claims.put("phone", phone);
        claims.put("code", code);
        return createToken(claims, expirationSeconds * 1000L);
    }

    public boolean validateSMSAuthToken(String token, String code) {
        if (!validationToken(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return claims.get("code").equals(code);
    }


    // jwt의 유효성 및 만료일자 확인
    public boolean validationToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date()); // 만료 날짜가 현재보다 이전이면 False
        } catch (JwtException | IllegalArgumentException e) {
            log.error(e.toString());
            return false;
        }
    }


    // Jwt 토큰 복호화해서 가져오기
    public Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
