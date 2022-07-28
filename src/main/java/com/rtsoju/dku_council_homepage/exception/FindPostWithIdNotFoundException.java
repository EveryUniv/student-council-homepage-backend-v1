package com.rtsoju.dku_council_homepage.exception;

public class FindPostWithIdNotFoundException extends RuntimeException{
    public FindPostWithIdNotFoundException() {
        super("해당 id와 일치하는 post가 없습니다.");
    }

    public FindPostWithIdNotFoundException(String message) {
        super(message);
    }

    public FindPostWithIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
