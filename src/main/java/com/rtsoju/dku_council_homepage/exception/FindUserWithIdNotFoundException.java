package com.rtsoju.dku_council_homepage.exception;

public class FindUserWithIdNotFoundException extends RuntimeException {
    public FindUserWithIdNotFoundException() {
        super("해당 유저를 찾을 수 없습니다.");
    }

    public FindUserWithIdNotFoundException(String message) {
        super(message);
    }

    public FindUserWithIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
