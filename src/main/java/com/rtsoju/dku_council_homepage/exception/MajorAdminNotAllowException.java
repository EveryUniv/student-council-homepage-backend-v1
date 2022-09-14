package com.rtsoju.dku_council_homepage.exception;

public class MajorAdminNotAllowException extends RuntimeException {
    public MajorAdminNotAllowException() {
        super("어드민 학과로는 회원가입을 할 수 없습니다.");
    }

    public MajorAdminNotAllowException(String message) {
        super(message);
    }

    public MajorAdminNotAllowException(String message, Throwable cause) {
        super(message, cause);
    }
}
