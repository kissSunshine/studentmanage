package com.zh.studentmanage.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum implements EnumInterface {
    TEACHER_NOT_FOUND("TeaErr01", "未查询到符合条件的教师信息"),

    CLASSES_NOT_FOUND("ClaErr01", "未查询到符合条件的班级信息"),
    CLASSES_ADD_FAIL("ClaErr02","添加班级失败！"),
    CLASSES_NAME_EXISTS("ClaErr03", "该班级名称已存在"),
    CLASSES_UPDATE_FAIL("ClaErr04", "修改班级失败"),
    CLASSES_DELETE_HAVE_STUDENT("ClaErr05", "删除班级失败，该班级还有学生，请先删除所有学生"),
    CLASSES_DELETE_FAIL("ClaErr06", "删除班级失败"),

    CLA_R_TEA_NOT_FOUND("CRT01", "未查询到符合条件的班级教师信息"),
    CLA_R_TEA_ADD_UPDATE_FAIL("CRT02", "添加或更新班级教师失败！"),
    CLA_R_TEA_ADD_TEACHERLIST_NULL("CRT03", "缺少班级教师信息！"),
    CLA_R_TEA_DELETE_FAIL("CRT04", "删除班级教师失败"),

    STUDENT_NO_ONE("StuErr01", "还没添加过学生，没有学生信息！"),
    ;

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
