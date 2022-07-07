package com.rtsoju.dku_council_homepage.common;

public class RequestResult {
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";

    private final String state;
    private final String message;

    public RequestResult(String message) {
        this(RESULT_SUCCESS, message);
    }

    public RequestResult(Exception e) {
        this(RESULT_ERROR, e.getMessage());
    }

    public RequestResult(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}