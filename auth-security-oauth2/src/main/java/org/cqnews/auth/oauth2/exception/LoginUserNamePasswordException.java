package org.cqnews.auth.oauth2.exception;

import lombok.Data;

@Data
public class LoginUserNamePasswordException extends RuntimeException {

    private static final String DERAULT_ERROR_MESSAGE = "操作成功";
    private static final int DERAULT_ERROR_CODE = -1;

    private String message;

    private Integer code;

    public LoginUserNamePasswordException(String message){
        super(message);
        this.message = message;
        this.code = DERAULT_ERROR_CODE;
    }

}
