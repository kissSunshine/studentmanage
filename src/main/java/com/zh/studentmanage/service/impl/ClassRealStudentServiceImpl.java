package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ClassRealStudentMapper;
import com.zh.studentmanage.pojo.ClassRealStudent;
import com.zh.studentmanage.service.ClassRealStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("classRealStudentService")
public class ClassRealStudentServiceImpl implements ClassRealStudentService {
    @Resource
    private ClassRealStudentMapper classRealStudentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClassRealStudent queryById(String id) {
        return this.classRealStudentMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ClassRealStudent> queryAllByLimit(int offset, int limit) {
        return this.classRealStudentMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param classRealStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ClassRealStudent insert(ClassRealStudent classRealStudent) {
        this.classRealStudentMapper.insert(classRealStudent);
        return classRealStudent;
    }

    /**
     * 修改数据
     *
     * @param classRealStudent 实例对象
     * @return 实例对象
     */
    @Override
    public ClassRealStudent update(ClassRealStudent classRealStudent) {
        this.classRealStudentMapper.update(classRealStudent);
        return this.queryById(classRealStudent.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.classRealStudentMapper.deleteById(id) > 0;
    }

    @Override
    public int haveClassStudent(String classId) {
        return classRealStudentMapper.haveClassStudent(classId);
    }
}
