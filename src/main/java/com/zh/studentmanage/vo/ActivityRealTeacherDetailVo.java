package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.AttendEnum;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityRealTeacherDetailVo {
    /**
     * Tea + 32位UUID，教师身份唯一标识
     */
    private String teacherid;
    private String nickname;

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
}

