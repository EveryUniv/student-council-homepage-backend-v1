package com.rtsoju.dku_council_homepage.domain.user.model.dto.request;

import lombok.Data;

@Data
public class RequestReissueDto {
    private String accessToken;
    private String refreshToken;
}
