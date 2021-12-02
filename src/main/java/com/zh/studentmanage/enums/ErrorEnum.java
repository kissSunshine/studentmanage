package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum implements EnumInterface {
    TEACHER_NOT_FOUND("TeaErr01", "未查询到符合条件的教师信息"),

    CLASSES_NOT_FOUND("ClaErr01", "未查询到符合条件的班级信息"),
    CLASSES_ADD_FAIL("ClaErr02","添加班级失败！"),
    CLASSES_NAME_EXISTS("ClaErr02", "该班级名称已存在"),
    CLASSES_UPDATE_FAIL("ClaErr03", "修改班级失败"),
    CLASSES_DELETE_HAVE_TEACHER("ClaErr04", "删除班级失败，该班级还有教师，请先删除所有教师"),
    CLASSES_DELETE_HAVE_STUDENT("ClaErr05", "删除班级失败，该班级还有学生，请先删除所有学生"),
    CLASSES_DELETE_FAIL("ClaErr06", "删除班级失败"),

    CLA_R_TEA_NOT_FOUND("CRT01", "未查询到符合条件的班级教师信息"),
    ;

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
