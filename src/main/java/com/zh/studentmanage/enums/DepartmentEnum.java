package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum DepartmentEnum {

    EDADMINISTRATION("01", "教务部门"),
    MARKET("02", "销售部门"),

    ;

    private final String code;
    private final String name;

    DepartmentEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
