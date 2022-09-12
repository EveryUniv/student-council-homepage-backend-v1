package com.rtsoju.dku_council_homepage.domain.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangePWDto {
    @NotBlank(message = "학번을 입력해주세요.")
    private String userId;

    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    private String newPW;
}
