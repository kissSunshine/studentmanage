package com.zh.studentmanage.pojo;

import java.util.Date;

public class Student {

    /**
     * Stu + 12位雪花号，学员身份唯一标识
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 昵称
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
     * 0-离校；1-在校
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
