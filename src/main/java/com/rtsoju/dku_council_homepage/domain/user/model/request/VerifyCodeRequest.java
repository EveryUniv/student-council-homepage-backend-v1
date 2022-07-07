package com.rtsoju.dku_council_homepage.domain.user.model.request;

public class VerifyCodeRequest {
    private String identifier;
    private String code;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
