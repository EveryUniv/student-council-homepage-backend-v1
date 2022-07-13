package com.rtsoju.dku_council_homepage.exception;

public class MajorDepartmentWrongException extends RuntimeException {
    public MajorDepartmentWrongException() {
    }

    public MajorDepartmentWrongException(String message) {
        super(message);
    }

    public MajorDepartmentWrongException(String message, Throwable cause) {
        super(message, cause);
    }
}
