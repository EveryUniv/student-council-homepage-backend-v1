package com.rtsoju.dku_council_homepage.exception;

import com.rtsoju.dku_council_homepage.common.ErrorResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult illegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(LoginUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult loginUserNotFoundException(HttpServletRequest request, LoginUserNotFoundException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(LoginPwdDifferentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult loginPwdDifferentException(HttpServletRequest request, LoginPwdDifferentException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(EmailUserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult emailUserExistException(HttpServletRequest request, EmailUserExistException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(ClassIdNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult classIdNotMatchException(HttpServletRequest request, ClassIdNotMatchException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(MajorDepartmentWrongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult majorDepartmentWrongException(HttpServletRequest request, MajorDepartmentWrongException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(RefreshTokenNotValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult refreshTokenNotValidate(HttpServletRequest request, RefreshTokenNotValidateException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(FindUserWithIdNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult findUserWithIdNotFoundException(HttpServletRequest request, FindUserWithIdNotFoundException e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(ReissueAccessTokenNotCorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult reissueAccessTokenNotCorrectException(HttpServletRequest request, ReissueAccessTokenNotCorrectException e) {
        return new ErrorResponseResult(e);
    }
}
