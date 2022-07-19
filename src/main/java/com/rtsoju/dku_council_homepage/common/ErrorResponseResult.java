package com.rtsoju.dku_council_homepage.common;

import lombok.Data;

/**
 * 요청 처리를 실패했을 때 돌려주는 DTO
 */
@Data
public class ErrorResponseResult implements ResponseResult {
    private final String message;

    public ErrorResponseResult(String message) {
        this.message = message;
    }

    public ErrorResponseResult(Exception e) {
        this(e.getMessage());
    }

    public boolean isSuccessful() {
        return false;
    }
}