package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL) //null 值不返回
public class ResponseVo<T> {

    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, String msg,T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseVo<T> success(String msg) {
        return new ResponseVo(1,msg);
    }

    public static <T> ResponseVo<T> success(String msg,List<T> List) {
        return new ResponseVo(1, msg, List);
    }

    public static <T> ResponseVo<T> success(String msg,Object object) {
        return new ResponseVo(1, msg, object);
    }

    public static <T> ResponseVo<T> error(String msg) {
        return new ResponseVo(0,msg);
    }

}
