package com.cqnews.auth.util;

import lombok.Data;

@Data
public class Result<T> {

    private static final String DERAULT_MESSAGE = "操作成功";
    private static final int DERAULT_CODE = 0;

    private static final String DERAULT_ERROR_MESSAGE = "操作成功";
    private static final int DERAULT_ERROR_CODE = -1;

    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result SUCCESS(T data){
        return new Result<>(DERAULT_CODE,DERAULT_MESSAGE,data);
    }

    public static <T> Result SUCCESS(String message){
        return new Result<>(DERAULT_CODE,message);
    }

    public static <T> Result FAIL(){
        return new Result<>(DERAULT_CODE,DERAULT_MESSAGE);
    }
    public static <T> Result FAIL(String message){
        return new Result<>(DERAULT_ERROR_CODE,message);
    }
}
