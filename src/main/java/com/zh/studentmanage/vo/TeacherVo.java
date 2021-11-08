package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.*;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;

@Data
public class TeacherVo {

    /**
     * Tea + 32位UUID，教师身份唯一标识
     */
    private String id;
    /**
     * 名称 20个字符
     */
    private String name;
    /**
     * 昵称 20个字符
     */
    private String nickname;
    /**
     * 形如：2021-10-01
     */
    private String birthday;
    /**
     * 0-女；1-男
     */
    private Integer gender;

    private String genderName;

    /**
     * 居民身份证号码
     */
    private String idcard;
    /**
     * 校区编号
     */
    private String schoolid;

    private String schoolName;

    /**
     * 手机号码
     */
    private String phone;
    /**
     * 家庭住址  500个字符
     */
    private String homeaddress;
    /**
     * 教授学科  2位数字编码
     */
    private String subject;

    private String subjectName;

    /**
     * 所属部门  2位数字编码
     */
    private String department;

    private String departmentName;

    /**
     * 职位  2位数字编码
     */
    private String position;

    private String positionName;

    /**
     * 0-离校；1-在校
     */
    private Integer status;

    private String statusName;

    /**
     * 修改信息操作人员
     */
    private String updatedPerson;

    @JsonIgnore
    public GenderEnum getGenderEnum(){
       return EnumUtil.getEnumName(gender, GenderEnum.class);
   }
    @JsonIgnore
    public SchoolEnum getSchoolEnum(){
        return EnumUtil.getEnumName(schoolid, SchoolEnum.class);
    }
    @JsonIgnore
    public SubjectEnum getSubjectEnum(){
        return EnumUtil.getEnumName(subject, SubjectEnum.class);
    }
    @JsonIgnore
    public DepartmentEnum getDepartmentEnum(){
        return EnumUtil.getEnumName(department, DepartmentEnum.class);
    }
    @JsonIgnore
    public PositionEnum getPositionEnum(){
        return EnumUtil.getEnumName(position, PositionEnum.class);
    }
    @JsonIgnore
    public StatusEnum getStatusEnum(){
        return EnumUtil.getEnumName(status, StatusEnum.class);
    }



}
