package com.rtsoju.dku_council_homepage.exception;

public class FileIsEmptyException extends RuntimeException{
    public FileIsEmptyException() {
        super("파일이 존재하지 않습니다.");
    }

    public FileIsEmptyException(String message) {
        super(message);
    }

    public FileIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
