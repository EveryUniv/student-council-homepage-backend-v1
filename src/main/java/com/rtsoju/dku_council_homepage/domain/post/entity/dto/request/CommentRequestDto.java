package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentRequestDto {
    @NotBlank(message = "댓글을 입력해주세요.")
    private String text;
}
