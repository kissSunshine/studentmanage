package com.zh.studentmanage.form;

import lombok.Data;

@Data
public class ClassesForm {
    /**
     * Cla + 32位UUID，班级唯一标识
     */
    private String id;
    /**
     * 名称：20个字
     */
    private String name;
    /**
     * 开班日期:形如：2021-10-01
     */
    private String birthday;
    /**
     * Tea + 12位雪花号，教师唯一标识
     */
    private String classmaster;
    /**
     * Sch + 32位UUID，校区身份唯一标识
     */
    private String schoolid;
    /**
     * 年级：一、二、三、四、五、六
     */
    private String grade;
    //语文教师
    private String yuwen;
    private String yuwenWeek;
    private String yuwenStartTime;
    private String yuwenEndTime;

    //数学教师
    private String math;
    private String mathWeek;
    private String mathStartTime;
    private String mathEndTime;

    //英语教师
    private String english;
    private String englishWeek;
    private String englishStartTime;
    private String englishEndTime;

    /**
     * 修改信息操作人员
     */
    private String updatedPerson;

}
