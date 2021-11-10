package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityRealTeacher implements Serializable {
    private static final long serialVersionUID = -96212154649410758L;
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;
    /**
     * Tea + 32位UUID，教师身份唯一标识
     */
    private String teacherid;
    /**
     * Sch + 12位雪花号，校区身份唯一标识
     */
    private String schoolid;
    /**
     * 开始日期:形如 2021-10-01
     */
    private String startdate;
    /**
     * 结束日期:形如 2021-10-01
     */
    private String enddate;
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

