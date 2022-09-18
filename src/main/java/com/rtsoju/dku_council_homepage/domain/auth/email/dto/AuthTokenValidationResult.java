package com.rtsoju.dku_council_homepage.domain.auth.email.dto;

public class AuthTokenValidationResult {
    private final boolean isValid;

    public AuthTokenValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
