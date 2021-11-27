package com.zh.studentmanage.vo;

import lombok.Data;

import java.util.List;

@Data
public class ActivityRealTeacherVo {

    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;

    /**
     * Sch + 32位雪花号，校区身份唯一标识
     */
    private String schoolid;

    /**
     * 存放每个活动下不同校区的多个教师
     */
    private List<ActivityRealTeacherDetailVo> activityRealTeacherDetailVo;
}

