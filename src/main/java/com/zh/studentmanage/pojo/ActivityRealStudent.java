package com.zh.studentmanage.pojo;

import java.io.Serializable;
import java.util.Date;

public class ActivityRealStudent implements Serializable {
    private static final long serialVersionUID = 405701489923907322L;
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;
    /**
     * Stu + 32位UUID，学员身份唯一标识
     */
    private String studentid;
    /**
     * 费用
     */
    private Double cost;
    /**
     * Sch + 12位雪花号，校区身份唯一标识
     */
    private String schoolid;
    /**
     * 0-未参加；1-已参加
     */
    private Integer attend;
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

