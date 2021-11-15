package com.zh.studentmanage.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityRealAddressVo {
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;
    private String activityName;

    /**
     * Sch + 32位UUID，校区身份唯一标识
     */
    private String schoolid;
    private String schoolName;

    /**
     * 详细活动地点
     */
    private String activityaddress;
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

