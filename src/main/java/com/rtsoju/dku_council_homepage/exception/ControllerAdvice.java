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

    @ExceptionHandler(EmailUserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult emailUserExistException(HttpServletRequest request, EmailUserExistException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(ClassIdNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult classIdNotMatchException(HttpServletRequest request, ClassIdNotMatchException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(MajorDepartmentWrongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult majorDepartmentWrongException(HttpServletRequest request, MajorDepartmentWrongException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(RefreshTokenNotValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult refreshTokenNotValidate(HttpServletRequest request, RefreshTokenNotValidateException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(FindUserWithIdNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult findUserWithIdNotFoundException(HttpServletRequest request, FindUserWithIdNotFoundException e) {
        return new RequestResult(e);
    }

    @ExceptionHandler(ReissueAccessTokenNotCorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected RequestResult reissueAccessTokenNotCorrectException(HttpServletRequest request, ReissueAccessTokenNotCorrectException e) {
        return new RequestResult(e);
    }
}
