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
    STUDENT_IMPORT_NAME_EMPTY("StuErr02", "学生姓名不可为空"),
    STUDENT_IMPORT_NICKNAME_EMPTY("StuErr03", "学生昵称不可为空"),
    STUDENT_IMPORT_BIRTHDAY_EMPTY("StuErr04", "生日不可为空"),
    STUDENT_IMPORT_GENDER_EMPTY("StuErr05", "性别不可为空"),
    STUDENT_IMPORT_SCHOOL_EMPTY("StuErr06", "校区不可为空"),
    STUDENT_IMPORT_PHONE_EMPTY("StuErr07", "手机号不可为空"),
    STUDENT_IMPORT_STATUS_EMPTY("StuErr08", "状态不可为空"),
    STUDENT_IMPORT_NAME_LENGTH("StuErr09", "姓名不可超过20个字符"),
    STUDENT_IMPORT_NICKNAME_LENGTH("StuErr10", "昵称不可超过20个字符"),
    STUDENT_IMPORT_GENDER_ERROR("StuErr11", "性别填写有误"),
    STUDENT_IMPORT_SCHOOL_ERROR("StuErr12", "校区填写有误"),
    STUDENT_IMPORT_STATUS_ERROR("StuErr13", "状态填写有误"),
    STUDENT_IMPORT_BIRTHDAY_ERROR("StuErr14", "生日填写有误"),
    STUDENT_IMPORT_ERROR("StuErr15", "导入数据库时出错"),
    STUDENT_IMPORT_NICKNAME_AGAIN("StuErr16", "昵称重复"),
    ;

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
