package com.rtsoju.dku_council_homepage.common.nhn.dto;

import lombok.Data;

@Data
public
class TokenRequest {
    private Auth auth = new Auth();

    @Data
    public static class Auth {
        private String tenantId;
        private PasswordCredentials passwordCredentials = new PasswordCredentials();
    }

    @Data
    public static class PasswordCredentials {
        private String username;
        private String password;
    }
}