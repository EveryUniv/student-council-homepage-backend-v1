package com.rtsoju.dku_council_homepage.common.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {


    JwtProvider jwtProvider = new JwtProvider();

    @Test
    public void 인증_성공() {
        //given
        String code = "5152";
        String token = jwtProvider.createToken(code);

        //when

        //then
        assertThat(jwtProvider.phoneValidate(token, code)).isTrue();
    }

    @Test
    public void 인증_실패() {
        //given
        String code = "5152";
        String code2 = "1234";
        String token = jwtProvider.createToken(code);

        //when

        //then
        assertThat(jwtProvider.phoneValidate(token, code2)).isFalse();
    }

}