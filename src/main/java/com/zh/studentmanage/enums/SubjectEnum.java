package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum SubjectEnum {
    YUWEN("01", "语文"),
    MATH("02", "数学"),
    ENGLISH("03", "英语"),
    PHYSICS("04", "物理"),
    CHEMISTRY("05", "化学"),
    BIOLOGY("06","生物"),
    POLITICS("07", "政治"),
    HISTORY("08", "历史"),
    GEOGRAPHY("09", "地理"),

    ;

    private final String code;
    private final String name;

    SubjectEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
