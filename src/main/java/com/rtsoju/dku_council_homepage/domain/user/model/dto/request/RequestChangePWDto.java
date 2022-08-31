package com.rtsoju.dku_council_homepage.domain.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RequestChangePWDto {
    @NotBlank(message = "전달받은 토큰을 보내주세요.")
    private String token;

    @NotBlank(message = "학번을 입력해주세요.")
    private String userId;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    private String newPW;
}
