package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ClassRealTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassRealTeacherMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ClassRealTeacher queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param classRealTeacher 查询条件
     * @return 对象列表
     */
    List<ClassRealTeacher> queryAllByLimit(@Param("classRealTeacher") ClassRealTeacher classRealTeacher,@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 统计总行数
     *
     * @param classRealTeacher 查询条件
     * @return 总行数
     */
    long count(ClassRealTeacher classRealTeacher);

    /**
     * 新增数据
     *
     * @param classRealTeacher 实例对象
     * @return 影响行数
     */
    int insert(ClassRealTeacher classRealTeacher);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClassRealTeacher> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ClassRealTeacher> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ClassRealTeacher> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ClassRealTeacher> entities);

    /**
     * 修改数据
     *
     * @param classRealTeacher 实例对象
     * @return 影响行数
     */
    int update(ClassRealTeacher classRealTeacher);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 根据班级id列表查询班级教师信息
     * @param classesidList 班级id列表
     * @return 班级教师信息
     */
    List<ClassRealTeacher> queryByIdBatch(@Param("classesidList")List<String> classesidList);

}

