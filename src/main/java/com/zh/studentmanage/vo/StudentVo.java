package com.zh.studentmanage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zh.studentmanage.enums.GenderEnum;
import com.zh.studentmanage.enums.StatusEnum;
import com.zh.studentmanage.utils.EnumUtil;
import lombok.Data;

@Data
public class StudentVo {

    /**
     * Stu + 32位UUID，学员身份唯一标识
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
    public StatusEnum getStatusEnum(){
        return EnumUtil.getEnumName(status, StatusEnum.class);
    }

}
