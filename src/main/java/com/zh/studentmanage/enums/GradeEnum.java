package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum GradeEnum implements EnumInterface {
    FIRST_GRADE("1", "一年级"),
    SECOND_GRADE("2", "二年级"),
    THREE_GRADE("3", "三年级"),
    FOURTH_GRADE("4", "四年级"),
    FIFTH_GRADE("5", "五年级"),
    SIXTH_GRADE("6", "六年级"),
    ;

    private final String code;
    private final String name;

    GradeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
