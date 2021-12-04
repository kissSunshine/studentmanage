package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.ClassRealStudent;

import java.util.List;

/**
 * (ClassRealStudent)表服务接口
 *
 * @author makejava
 * @since 2021-12-03 00:21:46
 */
public interface ClassRealStudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClassRealStudent queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClassRealStudent> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param classRealStudent 实例对象
     * @return 实例对象
     */
    ClassRealStudent insert(ClassRealStudent classRealStudent);

    /**
     * 修改数据
     *
     * @param classRealStudent 实例对象
     * @return 实例对象
     */
    ClassRealStudent update(ClassRealStudent classRealStudent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 通过班级id查询是否还有对应的学生信息
     * @param classId 班级id
     * @return 1-有；0-无
     */
    int haveClassStudent(String classId);

}
