package com.rtsoju.dku_council_homepage.domain.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BothTokenResponseDto {
    private String accessToken;
    private String refreshToken;

}
