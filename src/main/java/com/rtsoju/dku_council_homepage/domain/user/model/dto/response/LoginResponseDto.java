package com.rtsoju.dku_council_homepage.domain.user.model.dto.response;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
