package com.rtsoju.dku_council_homepage.exception;

public class ClassIdNotMatchException extends RuntimeException {
    public ClassIdNotMatchException() {
    }

    public ClassIdNotMatchException(String message) {
        super(message);
    }

    public ClassIdNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
