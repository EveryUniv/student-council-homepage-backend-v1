package com.rtsoju.dku_council_homepage.exception;

public class FindPostWithIdNotFoundException extends RuntimeException{
    public FindPostWithIdNotFoundException() {
    }

    public FindPostWithIdNotFoundException(String message) {
        super(message);
    }

    public FindPostWithIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
