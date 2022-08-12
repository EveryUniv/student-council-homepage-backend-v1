package com.rtsoju.dku_council_homepage.exception;

public class DuplicateCreatePetition extends RuntimeException{
    public DuplicateCreatePetition() {
        super();
    }

    public DuplicateCreatePetition(String message) {
        super(message);
    }

    public DuplicateCreatePetition(String message, Throwable cause) {
        super(message, cause);
    }
}
