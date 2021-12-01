package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum implements EnumInterface {
    TEACHER_NOT_FOUND("TeaErr01", "未查询到符合条件的教师信息"),
    CLASSES_NOT_FOUND("ClaErr01", "未查询到符合条件的班级信息"),
    CLA_R_TEA_NOT_FOUND("CRT01", "未查询到符合条件的班级教师信息"),
    ;

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
