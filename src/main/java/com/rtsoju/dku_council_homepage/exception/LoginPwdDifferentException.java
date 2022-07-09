package com.rtsoju.dku_council_homepage.exception;

public class LoginPwdDifferentException extends RuntimeException{
    public LoginPwdDifferentException() {
    }

    public LoginPwdDifferentException(String message) {
        super(message);
    }

    public LoginPwdDifferentException(String message, Throwable cause) {
        super(message, cause);
    }
}
