package com.zh.studentmanage.excelexport;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.GenderEnum;
import com.zh.studentmanage.enums.SchoolEnum;
import com.zh.studentmanage.enums.StatusEnum;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentExport {

    /**
     * 名称 20个字符
     */
    @Excel(name = "姓名",width = 15)
    private String name;
    /**
     * 昵称 20个字符
     */
    @Excel(name = "昵称",width = 15)
    private String nickname;
    /**
     * 形如：2021-10-01
     */
    @Excel(name = "出生日期",format = "yyyy-mm-dd",width = 15)
    private LocalDate birthday;
    /**
     * 0-女；1-男
     */
    private Integer gender;

    @Excel(name = "性别",width = 10)
    private String genderName;

    /**
     * 居民身份证号码
     */
    @Excel(name = "身份证号",width = 30)
    private String idcard;
    /**
     * 校区编号
     */
    private String schoolid;
    @Excel(name = "所属校区",width = 30)
    private String schoolName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号",width = 15)
    private String phone;
    /**
     * 家庭住址  500个字符
     */
    @Excel(name = "家庭住址",width = 50)
    private String homeaddress;
    /**
     * 0-离校；1-在校
     */
    private Integer status;
    @Excel(name = "状态",width = 15)
    private String statusName;

    @JsonIgnore
    public GenderEnum getGenderEnum(){
       return EnumUtil.getEnumName(gender, GenderEnum.class);
   }
    @JsonIgnore
    public StatusEnum getStatusEnum(){
        return EnumUtil.getEnumName(status, StatusEnum.class);
    }
    @JsonIgnore
    public SchoolEnum getSchoolEnum(){
        return EnumUtil.getEnumName(schoolid, SchoolEnum.class);
    }

}
