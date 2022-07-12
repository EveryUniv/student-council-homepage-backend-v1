package com.rtsoju.dku_council_homepage.exception;

import com.rtsoju.dku_council_homepage.common.RequestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(LoginUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult loginUserNotFoundException(HttpServletRequest request, LoginUserNotFoundException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(LoginPwdDifferentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult loginPwdDifferentException(HttpServletRequest request, LoginPwdDifferentException e) {
        return new RequestResult(e);
    }
}