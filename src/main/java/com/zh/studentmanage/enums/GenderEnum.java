package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum GenderEnum implements EnumInterface {
    MAN(1, "男"),
    WOMAN(0, "女")
    ;

    private final Integer code;
    private final String gender;

    GenderEnum(Integer code, String gender) {
        this.code = code;
        this.gender = gender;
    }

}
