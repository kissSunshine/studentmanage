package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Classes implements Serializable {
    private static final long serialVersionUID = -57740128799700185L;
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
    /**
     * 年级：一、二、三、四、五、六
     */
    private String grade;
}
