package com.zh.studentmanage.exception;

import com.zh.studentmanage.enums.ErrorEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private String code;
    private String message;

    public CustomException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

}
