package com.zh.studentmanage.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class School implements Serializable {
    private static final long serialVersionUID = -48268816273155533L;
    /**
     * Sch + 32位UUID，校区身份唯一标识
     */
    private String id;
    /**
     * 名称：20个字
     */
    private String name;
    /**
     * 建校日期:形如：2021-10-01
     */
    private String birthday;
    /**
     * 校区联系电话
     */
    private String phone;
    /**
     * 校区联系座机
     */
    private String telephone;
    /**
     * 校区地址
     */
    private String address;
    /**
     * 校区负责人
     */
    private String schoolmaster;
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
