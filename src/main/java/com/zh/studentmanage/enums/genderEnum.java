package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum genderEnum {
    MAN(1, "男"),
    WOMAN(0, "女")
    ;

    private Integer code;
    private String gender;

    genderEnum(Integer code, String gender) {
        this.code = code;
        this.gender = gender;
    }
}
