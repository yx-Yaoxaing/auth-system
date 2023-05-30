package com.cqnews.auth.config;

import com.cqnews.auth.exception.LoginUserNamePasswordException;
import org.cqnews.common.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */

@SuppressWarnings("ALL")
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(LoginUserNamePasswordException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result bindException(LoginUserNamePasswordException exception) {
        return Result.FAIL(exception.getMessage());
    }


}
