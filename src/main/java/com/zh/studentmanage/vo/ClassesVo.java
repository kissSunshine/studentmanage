package com.zh.studentmanage.vo;

import lombok.Data;

@Data
public class ClassesVo {
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
    private String classmasterName;
    /**
     * Sch + 32位UUID，校区身份唯一标识
     */
    private String schoolid;
    /**
     * 年级：一、二、三、四、五、六
     */
    private String grade;

    //语文
    private String yuwenTeacher;

    //数学
    private String mathTeacher;

    //英语
    private String englishTeacher;

}
