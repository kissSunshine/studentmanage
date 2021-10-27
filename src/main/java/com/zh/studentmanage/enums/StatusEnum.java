package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum StatusEnum implements EnumInterface {
    AT_SCHOOL(1, "在校"),
    LEAVE_SCHOOL(0, "离校")
    ;

    private final Integer code;
    private final String name;

    StatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
