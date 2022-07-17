package com.rtsoju.dku_council_homepage.domain.auth.sms.dto.request;

public class VerifyCodeRequest {
    private String token;
    private String code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
