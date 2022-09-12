package com.rtsoju.dku_council_homepage.exception;

public class NotFoundCommentException extends RuntimeException{
    public NotFoundCommentException() {
        super("댓글을 찾지 못했습니다.");
    }

    public NotFoundCommentException(String message) {
        super(message);
    }

    public NotFoundCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
