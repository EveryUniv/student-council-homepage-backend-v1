package com.rtsoju.dku_council_homepage.exception;

public class FindCommentWithPostAndUserException extends RuntimeException{
    public FindCommentWithPostAndUserException() {
        super("댓글을 찾지 못했습니다.");
    }

    public FindCommentWithPostAndUserException(String message) {
        super(message);
    }

    public FindCommentWithPostAndUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
