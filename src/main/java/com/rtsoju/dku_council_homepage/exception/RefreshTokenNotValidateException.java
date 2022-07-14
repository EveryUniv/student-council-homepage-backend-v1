package com.rtsoju.dku_council_homepage.exception;

public class RefreshTokenNotValidateException extends RuntimeException {
    public RefreshTokenNotValidateException() {
        super("refreshtoken이 유효하지 않습니다.");
    }

    public RefreshTokenNotValidateException(String message) {
        super(message);
    }

    public RefreshTokenNotValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
