package com.rtsoju.dku_council_homepage.exception;

public class NotAllowedUpdateException extends RuntimeException{
    public NotAllowedUpdateException() {
        super("수정이 불가합니다.");
    }

    public NotAllowedUpdateException(String message) {
        super(message);
    }

    public NotAllowedUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
