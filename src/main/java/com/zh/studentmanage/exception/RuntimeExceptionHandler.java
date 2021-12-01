package com.zh.studentmanage.exception;

import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseVo handler(CustomException e) {
        return ResponseVo.error(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVo notValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return null;
    }


}
