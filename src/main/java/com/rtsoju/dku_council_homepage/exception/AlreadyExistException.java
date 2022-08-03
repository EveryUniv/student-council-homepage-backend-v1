package com.rtsoju.dku_council_homepage.exception;

public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException() {
        super("이미 존재합니다.");
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
