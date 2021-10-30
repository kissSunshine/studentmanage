package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Activity implements Serializable {
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
    private Date startdate;
    /**
     * 开始时间:形如 08：30：00
     */
    private Date starttime;
    /**
     * 结束日期:形如 2021-10-01
     */
    private Date enddate;
    /**
     * 结束时间:形如 08：30：00
     */
    private Date endtime;
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
