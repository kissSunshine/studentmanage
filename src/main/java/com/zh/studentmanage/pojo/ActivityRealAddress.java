package com.zh.studentmanage.pojo;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class ActivityRealAddress implements Serializable {
    private static final long serialVersionUID = -21170566690129924L;
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;
    /**
     * Sch + 32位UUID，校区身份唯一标识
     */
    private String schoolid;
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

