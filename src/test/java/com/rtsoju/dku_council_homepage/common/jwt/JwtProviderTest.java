package com.rtsoju.dku_council_homepage.common.jwt;

import com.rtsoju.dku_council_homepage.domain.auth.service.CustomUserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    @Autowired
    JwtProvider jwtProvider;

    @Test
    public void SMS_인증_성공() {
        //given
        String phone = "01012345678";
        String code = "5152";
        String token = jwtProvider.createSMSAuthToken(phone, code);

        //when

        //then
        assertThat(jwtProvider.validateSMSAuthToken(token, code)).isTrue();
    }

    @Test
    public void SMS_인증_실패() {
        //given
        String phone = "01012345678";
        String code = "5152";
        String token = jwtProvider.createSMSAuthToken(phone, code);

        //when

        //then
        String notMatchedCode = "1231";
        assertThat(jwtProvider.validateSMSAuthToken(token, notMatchedCode)).isFalse();
    }

}