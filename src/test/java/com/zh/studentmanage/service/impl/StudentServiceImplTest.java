package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.StudentmanageApplicationTests;
import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.pojo.Student;
import org.junit.Test;
import javax.annotation.Resource;

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
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }
}