package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClassRealStudent implements Serializable {
    private static final long serialVersionUID = -83908308567952346L;
    /**
     * CRS + 32位UUID，唯一标识
     */
    private String id;
    /**
     * Stu + 32位UUID，教师身份唯一标识
     */
    private String studentid;
    /**
     * Cla + 32位UUID，班级唯一标识
     */
    private String classid;
    /**
     * 学习学科
     */
    private String subject;
    /**
     * 上课日期：比如 1-周一
     */
    private String week;
    /**
     * 开始时间:形如 08：30：00
     */
    private Object starttime;
    /**
     * 结束时间:形如 08：30：00
     */
    private Object endtime;
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
