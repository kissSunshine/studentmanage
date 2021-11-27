package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum ActivityStatusEnum implements EnumInterface {
    BEFORE_BEGIN(0, "未开始"),
    BEGINING(1, "活动中"),
    END(2, "已结束"),
    ;

    private final Integer code;
    private final String name;

    ActivityStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
