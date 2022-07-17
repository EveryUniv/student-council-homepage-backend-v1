package com.rtsoju.dku_council_homepage.common.nhn.service;

import com.rtsoju.dku_council_homepage.common.ExternalURLs;
import com.rtsoju.dku_council_homepage.common.nhn.dto.TokenRequest;
import com.rtsoju.dku_council_homepage.common.nhn.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final TokenRequest tokenRequest;
    private final RestTemplate restTemplate;

    public AuthService(
            @Value("${nhn.os.tenantId}") String tenantId,
            @Value("${nhn.os.username}") String username,
            @Value("${nhn.os.password}") String password) {
        this.tokenRequest = new TokenRequest();
        this.tokenRequest.getAuth().setTenantId(tenantId);
        this.tokenRequest.getAuth().getPasswordCredentials().setUsername(username);
        this.tokenRequest.getAuth().getPasswordCredentials().setPassword(password);

        this.restTemplate = new RestTemplate();
    }

    public String requestToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<TokenRequest> httpEntity = new HttpEntity<>(this.tokenRequest, headers);
        ResponseEntity<TokenResponse> response = this.restTemplate.exchange(
                ExternalURLs.NHNAuth, HttpMethod.POST, httpEntity, TokenResponse.class);
        TokenResponse token = response.getBody();

        if (token == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return token.getTokenId();
    }
}