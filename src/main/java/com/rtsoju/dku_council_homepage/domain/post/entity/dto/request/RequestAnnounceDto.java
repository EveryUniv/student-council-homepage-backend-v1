package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true) //중대한 오류가 있었음.
public class RequestAnnounceDto extends RequestPostDto {
    @NotBlank(message = "category(카테고리)를 지정해주세요")
    private String category;
}
