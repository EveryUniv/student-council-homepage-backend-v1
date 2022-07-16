package com.rtsoju.dku_council_homepage.common.nhn.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private Access access = new Access();

    public String getTokenId() {
        return access.token.id;
    }

    @Data
    public static class Access {
        private Token token = new Token();
    }

    @Data
    public static class Token {
        private String id;
    }
}