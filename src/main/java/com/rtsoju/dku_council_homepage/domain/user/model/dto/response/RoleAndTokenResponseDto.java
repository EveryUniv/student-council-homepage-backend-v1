package com.rtsoju.dku_council_homepage.domain.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoleAndTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private boolean isAdmin;

}
