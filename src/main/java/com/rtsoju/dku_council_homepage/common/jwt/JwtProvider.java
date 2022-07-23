package com.rtsoju.dku_council_homepage.common.jwt;

import com.rtsoju.dku_council_homepage.domain.auth.service.CustomUserDetailService;
import com.rtsoju.dku_council_homepage.exception.ReissueAccessTokenNotCorrectException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final CustomUserDetailService customUserDetailService;

    @Value("temp")
    private String secretKey = "temp";

    @Value("${auth.sms.expirationSeconds}")
    private int expirationSeconds;

    private final Long accessTokenValidMillisecond = 24 * 60 * 1000L; // 1 hour
    private final Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day

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

    public String createLoginAccessToken(Long userId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.setSubject(userId.toString());
        claims.put("roles", roles);

        return createToken(claims, accessTokenValidMillisecond);
    }

    public String createEmailValidationToken(String classId){
        Claims claims = Jwts.claims();
//        claims.setSubject("ClassId");
        claims.put("classId",classId);

        return createToken(claims, accessTokenValidMillisecond);
    }

    public String createLoginRefreshToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.setSubject(userId.toString());

        return createToken(claims, refreshTokenValidMillisecond);
    }

    public boolean validateSMSAuthToken(String token, String code) {
        if (!validationToken(token)) {
            return false;
        }

        Claims claims = parseClaims(token);
        return claims.get("code").equals(code);
    }

    //회원가입시, 전달받은 토큰과 학번으로 유효성 검사 실시해야함.
    public boolean validateEmailValidationToken(String token, String classId){
        if(!validationToken(token)){
            return false;
        }
        Claims claims = parseClaims(token);
        return claims.get("classId").equals(classId);
    }


    // jwt의 유효성 및 만료일자 확인
    public boolean validationToken(String token) {
        // Todo: 아예 잘못된 토큰인지 or 만료된 토큰인지 에러처리를 할까 말까....
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date()); // 만료 날짜가 현재보다 이전이면 False
        } catch (JwtException | IllegalArgumentException e) {
            log.error(e.toString());
            return false;
        }
    }


    // Jwt 토큰 복호화해서 body(payload) 가져오기
    public Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (SignatureException e) {
            throw new ReissueAccessTokenNotCorrectException("Access Token 이 올바르지 않습니다.");
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 id 추출
    public String getUserId(String token) {
        return parseClaims(token).getSubject();
    }



}
