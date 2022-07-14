package com.rtsoju.dku_council_homepage.exception;

public class ReissueAccessTokenNotCorrectException extends RuntimeException {
    public ReissueAccessTokenNotCorrectException() {
    }

    public ReissueAccessTokenNotCorrectException(String message) {
        super(message);
    }

    public ReissueAccessTokenNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
