package com.rtsoju.dku_council_homepage.exception;

import com.rtsoju.dku_council_homepage.common.ErrorResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({IllegalArgumentException.class,
            LoginUserNotFoundException.class,
            LoginPwdDifferentException.class,
            EmailUserExistException.class,
            ClassIdNotMatchException.class,
            MajorDepartmentWrongException.class,
            RefreshTokenNotValidateException.class,
            FindUserWithIdNotFoundException.class,
            ReissueAccessTokenNotCorrectException.class,
            FindPostWithIdNotFoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponseResult exceptionHandler(HttpServletRequest request, Exception e) {
        return new ErrorResponseResult(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return new ErrorResponseResult(errorMessage);
    }
}
