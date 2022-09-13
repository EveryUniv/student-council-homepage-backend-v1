package com.rtsoju.dku_council_homepage.exception;

public class NotMatchWriterException extends RuntimeException {
    public NotMatchWriterException() {
        super("작성자가 아닙니다.");
    }

    public NotMatchWriterException(String message) {
        super(message);
    }

    public NotMatchWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
