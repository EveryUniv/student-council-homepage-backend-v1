package com.rtsoju.dku_council_homepage.common;

import java.util.ArrayList;
import java.util.List;

public class RequestResult {
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";

    private final String state;
    private final String message;
    private List<Object> data = new ArrayList<>();

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

    public RequestResult(String message, Object data) {
        this(RESULT_SUCCESS, message, data);
    }

    public RequestResult(Exception e, Object data) {
        this(RESULT_ERROR, e.getMessage(), data);
    }

    public RequestResult(String state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data.add(data);
    }

    public String getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() { return data; }
}