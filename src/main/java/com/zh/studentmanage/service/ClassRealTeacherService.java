package com.zh.studentmanage.service;


import com.zh.studentmanage.pojo.ClassRealTeacher;

import java.util.List;

public interface ClassRealTeacherService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClassRealTeacher queryById(String id);

    /**
     * 新增数据
     *
     * @param classRealTeacher 实例对象
     * @return 实例对象
     */
    ClassRealTeacher insert(ClassRealTeacher classRealTeacher);

    /**
     * 修改数据
     *
     * @param classRealTeacher 实例对象
     * @return 实例对象
     */
    ClassRealTeacher update(ClassRealTeacher classRealTeacher);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 通过ID查询单条数据
     *
     * @param classesidList 主键
     * @return 实例对象
     */
    List<ClassRealTeacher> queryByClassIdBatch(List<String> classesidList);

    /**
     * 通过班级id查询是否还有对应的老师信息
     * @param classId 班级id
     * @return 1-有；0-无
     */
    Integer haveClassTeacher(String classId);
}
