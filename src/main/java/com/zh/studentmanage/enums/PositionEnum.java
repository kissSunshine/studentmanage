package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum PositionEnum implements EnumInterface{
    PRINCIPAL("01", "校长"),
    VICE_PRINCIPAL("02", "副校长"),

    TEACHER_DIRECTOR("11", "教导主任"),
    TEACHER("12", "教师"),

    COUNSELOR_DIRECTOR("21", "咨询主任"),
    COUNSELOR("22", "咨询员"),

    ;

    private final String code;
    private final String name;

    PositionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
