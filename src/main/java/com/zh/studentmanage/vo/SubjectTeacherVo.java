package com.zh.studentmanage.vo;

import lombok.Data;

@Data
public class SubjectTeacherVo {

    /**
     * Tea + 32位UUID，教师身份唯一标识
     */
    private String id;
    /**
     * 昵称 20个字符
     */
    private String nickname;
}
