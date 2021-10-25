package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.StudentmanageApplicationTests;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ResponseVo;
import org.junit.Test;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TeacherServiceImplTest extends StudentmanageApplicationTests {

    @Resource
    private TeacherService teacherService;

    @Test
    public void insert() {
        Teacher teacher = new Teacher();
        teacher.setId("Tea1234");
        teacher.setName("Mr.zhang");
        teacher.setNickname("大熊");
        teacher.setPassword("666");

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        teacher.setBirthday("1993-05-23");

        teacher.setGender(1);
        teacher.setIdcard("5002341");
        teacher.setSchoolid("Sch0001");
        teacher.setPhone("13780886954");
        teacher.setHomeaddress("阳光花园");
        teacher.setSubject("01");
        teacher.setDepartment("01");
        teacher.setPosition("01");
        teacher.setStatus(1);
        teacher.setUpdatedPerson("Tea001");

        ResponseVo responseVo = teacherService.insert(teacher);
        System.out.println(responseVo);
    }
}