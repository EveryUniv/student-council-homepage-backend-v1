package com.rtsoju.dku_council_homepage.exception;

public class FindUserWithIdNotFoundException extends RuntimeException {
    public FindUserWithIdNotFoundException() {
        super("토큰 재발급시 accesstoken에 저장된 userid 값을 찾을 수 없습니다.");
    }

    public FindUserWithIdNotFoundException(String message) {
        super(message);
    }

    public FindUserWithIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
