package com.zh.studentmanage.dao;

import com.zh.studentmanage.StudentmanageApplicationTests;
import com.zh.studentmanage.pojo.Teacher;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherMapperTest extends StudentmanageApplicationTests {

    @Resource
    private TeacherMapper teacherMapper;

    @Test
    public void queryById() {
        Teacher teacher = teacherMapper.queryById("Tea123");
        System.out.println(teacher);
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
    public void changeStatusById() {
    }

    @Test
    public void queryByNickname() {
    }

    @Test
    public void queryByIdBatch() {
        List<String> teacherIdList = new ArrayList<>();
        teacherIdList.add("1");
        teacherIdList.add("2");

        List<Teacher> teacherList = teacherMapper.queryByIdBatch(teacherIdList);
        System.out.println(teacherList);
    }
}