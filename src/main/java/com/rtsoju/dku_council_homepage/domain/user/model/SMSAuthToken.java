package com.rtsoju.dku_council_homepage.domain.user.model;

public class SMSAuthToken {
    private final String token;

    public SMSAuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
