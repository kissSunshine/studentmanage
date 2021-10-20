package com.zh.studentmanage;

import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentmanageApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void contextLoads() {
        Student student = studentMapper.queryById("Stu123456789012");
        System.out.println(student);
    }

}
