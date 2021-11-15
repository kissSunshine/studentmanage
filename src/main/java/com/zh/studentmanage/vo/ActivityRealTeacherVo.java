package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.AttendEnum;
import com.zh.studentmanage.enums.StatusEnum;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityRealTeacherVo {
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String activityid;
    private String activityName;

    /**
     * Tea + 32位UUID，教师身份唯一标识
     */
    private String teacherid;
    private String teacherName;

    /**
     * Sch + 12位雪花号，校区身份唯一标识
     */
    private String schoolid;
    private String schoolName;

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
    private String attendName;

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

    @JsonIgnore
    public AttendEnum  getAttendEnum(){
        return EnumUtil.getEnumName(attend, AttendEnum.class);
    }

}

