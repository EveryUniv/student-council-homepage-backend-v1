package com.rtsoju.dku_council_homepage.exception;

import com.rtsoju.dku_council_homepage.common.ErrorResponseResult;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
            FindPostWithIdNotFoundException.class,
            BadRequestException.class,
            EmptyResultDataAccessException.class, // JpaRepository error -> directory 다 반환되는데 어케 처리할지..
            DuplicateCommentException.class,
            DuplicateCreatePetition.class,
            DuplicateSignInException.class,
            AlreadyExistException.class,
            CannotReadResourceException.class,
            InvalidKeyException.class,
            FindCategoryWithIdNotFoundException.class,
            FileIsEmptyException.class,
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

    @ExceptionHandler(BindException.class)
    public ErrorResponseResult handleBindException(BindException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return new ErrorResponseResult(errorMessage);
    }
}
