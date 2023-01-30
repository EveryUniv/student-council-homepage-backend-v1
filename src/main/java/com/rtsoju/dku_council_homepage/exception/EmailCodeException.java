package com.rtsoju.dku_council_homepage.exception;

public class EmailCodeException extends RuntimeException{
    public EmailCodeException() {
        super("인증시간이 만료되었습니다.");
    }

    public EmailCodeException(String message) {
        super(message);
    }

    public EmailCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
