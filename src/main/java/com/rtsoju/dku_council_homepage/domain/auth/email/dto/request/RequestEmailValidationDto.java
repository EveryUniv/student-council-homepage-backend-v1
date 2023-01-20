package com.rtsoju.dku_council_homepage.domain.auth.email.dto.request;

import lombok.Data;

@Data
public class RequestEmailValidationDto {
    private String classId;
    private String code;
}
