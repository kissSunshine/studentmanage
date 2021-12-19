package com.zh.studentmanage.enums;

import lombok.Getter;

/**
 * 分页用
 */
@Getter
public enum PageEnum {
    TOTAL("total", "查询总量"),
    DATA("dataResult", "查询的数据"),
    ;

    private String code;
    private String message;

    PageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
