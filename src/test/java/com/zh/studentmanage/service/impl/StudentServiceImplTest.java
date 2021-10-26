package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.StudentmanageApplicationTests;
import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.vo.ResponseVo;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class StudentServiceImplTest extends StudentmanageApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void queryById() {
        Student student = studentMapper.queryById("124");
        System.out.println(student);
    }

    @Test
    public void insert() {
        String id = "Stu" + UUID.randomUUID().toString().replace("-", "");
        System.out.println(id);

    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }
}