package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClassRealTeacher implements Serializable {
    private static final long serialVersionUID = -26085103824571434L;
    /**
     * CRT + 32位雪花号，唯一标识
     */
    private String id;
    /**
     * Tea + 32位雪花号，教师身份唯一标识
     */
    private String teacherid;
    /**
     * Cla + 12位雪花号，班级唯一标识
     */
    private String classid;
    /**
     * 教授学科
     */
    private String subject;
    /**
     * 比如：1-周一
     */
    private String week;
    /**
     * 开始时间:形如 08：30：00
     */
    private String starttime;
    /**
     * 结束时间:形如 08：30：00
     */
    private String endtime;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 修改信息操作人员
     */
    private String updatedPerson;

}

