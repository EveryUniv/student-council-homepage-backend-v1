package com.rtsoju.dku_council_homepage.exception;

public class DuplicateCreatePost extends RuntimeException{
    public DuplicateCreatePost() {
        super();
    }

    public DuplicateCreatePost(String message) {
        super(message);
    }

    public DuplicateCreatePost(String message, Throwable cause) {
        super(message, cause);
    }
}
