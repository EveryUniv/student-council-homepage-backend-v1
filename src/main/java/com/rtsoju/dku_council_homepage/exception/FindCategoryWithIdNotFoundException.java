package com.rtsoju.dku_council_homepage.exception;

public class FindCategoryWithIdNotFoundException extends RuntimeException{
    public FindCategoryWithIdNotFoundException() {
        super("존재하지 않는 카테고리입니다.");
    }

    public FindCategoryWithIdNotFoundException(String message) {
        super(message);
    }

    public FindCategoryWithIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
