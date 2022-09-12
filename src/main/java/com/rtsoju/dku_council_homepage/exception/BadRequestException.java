package com.rtsoju.dku_council_homepage.exception;

import org.hibernate.cfg.Environment;

import java.util.function.Supplier;

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
