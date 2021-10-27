package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum SchoolEnum implements EnumInterface {
    LIJIATUO("Sch001", "李家沱校区"),
    XIEJIAWAN("Sch002", "谢家湾校区"),
    YANGJIAPING("Sch003", "杨家坪校区"),
    DADUKOU("Sch004", "大渡口校区"),
    YUBEI("Sch005", "渝北校区"),
    SHAPINGBA("Sch006", "沙坪坝校区"),
    JIUJIE("Sch007", "九街校区"),
    ;

    private final String code;
    private final String name;

    SchoolEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
