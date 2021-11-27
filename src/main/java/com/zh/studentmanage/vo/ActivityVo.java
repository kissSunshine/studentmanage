package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.ActivityStatusEnum;
import com.zh.studentmanage.enums.GenderEnum;
import com.zh.studentmanage.enums.StatusEnum;
import com.zh.studentmanage.pojo.ActivityRealAddress;
import com.zh.studentmanage.pojo.ActivityRealTeacher;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ActivityVo{
    /**
     * Act + 32位UUID，不同活动唯一标识
     */
    private String id;
    /**
     * 名称：20个字
     */
    private String name;
    /**
     * 开始日期:形如 2021-10-01
     */
    private String startDateTime;
    /**
     * 结束日期:形如 2021-10-01
     */
    private String endDateTime;
    /**
     * 费用
     */
    private BigDecimal cost;
    /**
     * 折扣，百分数
     */
    private BigDecimal discount;
    /**
     * 0-未开始；1-活动中；2-已结束
     */
    private Integer status;
    private String statusName;

    /**
     * 活动地点
     */
    private List<ActivityRealAddressVo> activityRealAddressVoList;

    /**
     * 活动教师
     */
    private List<ActivityRealTeacherVo> activityRealTeacherVoList;

    /**
     * 活动说明
     */
    private String remark;
    /**
     * 修改信息操作人员
     */
    private String updatedPerson;

    @JsonIgnore
    public ActivityStatusEnum getStatusEnum(){
        return EnumUtil.getEnumName(status, ActivityStatusEnum.class);
    }

}
