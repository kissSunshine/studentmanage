package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void queryById() {
    }

    @Test
    public void queryByParam() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertBatch() {
    }

    @Test
    public void insertOrUpdateBatch() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void queryByNickname() {
        Student student = studentMapper.queryByNickname("帅帅");
        System.out.print(student);

    }
}