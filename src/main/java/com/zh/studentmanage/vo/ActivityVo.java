package com.zh.studentmanage.vo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ActivityVo implements Serializable {
    private static final long serialVersionUID = 123064456691250877L;
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
     * 活动地点
     */
    private String address;
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
    /**
     * 活动说明
     */
    private String remark;
    /**
     * 修改信息操作人员
     */
    private String updatedPerson;

}
