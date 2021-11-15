package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum AttendEnum implements EnumInterface {
    ATTEND(1, "已参加"),
    NOT_ATTEND(0, "未参加")
    ;

    private final Integer code;
    private final String name;

    AttendEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
