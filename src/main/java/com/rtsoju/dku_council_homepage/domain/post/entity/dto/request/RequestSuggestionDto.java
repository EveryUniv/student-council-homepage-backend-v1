package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestSuggestionDto extends RequestPostDto {
    @NotBlank(message = "category(카테고리)를 지정해주세요")
    private String category;
}
