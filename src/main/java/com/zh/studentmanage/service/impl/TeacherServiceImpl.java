package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.TeacherMapper;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.service.TeacherService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (Teacher)表服务实现类
 *
 * @author makejava
 * @since 2021-10-25 14:37:22
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher queryById(String id) {
        return null;
    }

    @Override
    public Teacher insert(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher update(Teacher teacher) {
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
