package com.rtsoju.dku_council_homepage.exception;

public class LoginUserNotFoundException extends RuntimeException{
    public LoginUserNotFoundException() {
    }

    public LoginUserNotFoundException(String message) {
        super(message);
    }

    public LoginUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
