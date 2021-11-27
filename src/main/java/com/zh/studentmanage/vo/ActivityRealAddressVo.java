package com.zh.studentmanage.vo;

import lombok.Data;

@Data
public class ActivityRealAddressVo {
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
}

