package com.rtsoju.dku_council_homepage.exception;

public class DuplicateSignInException extends RuntimeException{
    public DuplicateSignInException() {
        super("이미 회원가입이 되어있는 회원입니다.");
    }

    public DuplicateSignInException(String message) {
        super(message);
    }

    public DuplicateSignInException(String message, Throwable cause) {
        super(message, cause);
    }
}
