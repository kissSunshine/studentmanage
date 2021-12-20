package com.zh.studentmanage.exception;

import com.zh.studentmanage.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final String code;
    private final String message;

    public CustomException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public CustomException(String code,String message) {
        this.code = code;
        this.message = message;
    }

}
