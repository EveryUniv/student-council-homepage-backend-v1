package com.rtsoju.dku_council_homepage.domain.auth.email.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class EmailResponseDto {
    private String classId;
    public EmailResponseDto(String classId) {
        this.classId = classId;
    }
}
