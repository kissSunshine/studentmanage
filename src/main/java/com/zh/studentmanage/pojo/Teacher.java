package com.zh.studentmanage.pojo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class Teacher implements Serializable {
    private static final long serialVersionUID = -87760096675781309L;
    /**
     * Tea + 12位雪花号，教师身份唯一标识
     */
    private String id;
    /**
     * 名称：20个字
     */
    private String name;
    /**
     * 昵称：20个字
     */
    private String nickname;
    /**
     * 6-20位，MD5加密
     */
    private String password;
    /**
     * 形如：2021-10-01
     */
    private Date birthday;
    /**
     * 0-女；1-男
     */
    private Integer gender;
    /**
     * 居民身份证号码
     */
    private String idcard;
    /**
     * 校区编号
     */
    private String schoolid;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 家庭住址
     */
    private String homeaddress;
    /**
     * 教授学科
     */
    private String subject;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 职位
     */
    private String position;
    /**
     * 0-离职；1-在职
     */
    private Integer status;
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
