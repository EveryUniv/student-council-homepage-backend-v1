package com.rtsoju.dku_council_homepage.common;

import lombok.Data;

/**
 * 요청 처리가 성공적으로 끝났을 때 돌려주는 DTO
 */
@Data
public class SuccessResponseResult implements ResponseResult {
    private final String message;
    private final Object data;

    public SuccessResponseResult() {
        this("", new Object[0]);
    }

    public SuccessResponseResult(String message) {
        this(message, new Object[0]);
    }

    public SuccessResponseResult(Object data) {
        this("", data);
    }

    public SuccessResponseResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public boolean isSuccessful() {
        return true;
    }
}