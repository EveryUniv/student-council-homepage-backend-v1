package com.rtsoju.dku_council_homepage.exception;

public class CannotReadResourceException extends RuntimeException {
    public CannotReadResourceException(String message) {
        super(message);
    }

    public CannotReadResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
