package com.rtsoju.dku_council_homepage.domain.auth.email.dto.request;

import lombok.Data;

@Data
public class EmailResponseDto {
    private final String classId;
    private final String emailValidationToken;
    public EmailResponseDto(String classId, String emailValidationToken) {
        this.classId = classId;
        this.emailValidationToken = emailValidationToken;
    }

}
