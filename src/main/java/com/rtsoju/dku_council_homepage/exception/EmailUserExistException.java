package com.rtsoju.dku_council_homepage.exception;

public class EmailUserExistException extends RuntimeException{
    public EmailUserExistException() {
    }

    public EmailUserExistException(String message) {
        super(message);
    }

    public EmailUserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
