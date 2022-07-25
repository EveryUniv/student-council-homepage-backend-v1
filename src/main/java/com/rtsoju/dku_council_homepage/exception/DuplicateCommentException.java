package com.rtsoju.dku_council_homepage.exception;

public class DuplicateCommentException extends RuntimeException{
    public DuplicateCommentException() {
        super("이미 댓글을 작성하였습니다.");
    }

    public DuplicateCommentException(String message) {
        super(message);
    }

    public DuplicateCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
