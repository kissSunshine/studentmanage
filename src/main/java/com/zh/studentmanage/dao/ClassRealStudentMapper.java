package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ClassRealStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ClassRealStudent)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-03 00:21:45
 */
public interface ClassRealStudentMapper {

    /**
     * 通过班级id查询班级学生信息
     *
     * @param classesId 班级id
     * @return 实例对象
     */
    List<ClassRealStudent> queryByClassesId(String classesId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ClassRealStudent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param classRealStudent 实例对象
     * @return 对象列表
     */
    List<ClassRealStudent> queryAll(ClassRealStudent classRealStudent);

    /**
     * 新增数据
     *
     * @param classRealStudent 实例对象
     * @return 影响行数
     */
    int insert(ClassRealStudent classRealStudent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClassRealStudent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ClassRealStudent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClassRealStudent> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<ClassRealStudent> entities);

    /**
     * 修改数据
     *
     * @param classRealStudent 实例对象
     * @return 影响行数
     */
    int update(ClassRealStudent classRealStudent);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过班级id查询是否还有对应的学生信息
     * @param classId 班级id
     * @return 1-有；0-无
     */
    int haveClassStudent(String classId);

    /**
     * 通过班级id查询班级人数
     * @param classesId 班级id
     * @return 人数
     */
    int queryCountByClassesId(String classesId);


}

