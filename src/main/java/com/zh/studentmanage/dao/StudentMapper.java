package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Student queryById(String id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryByParam(Student student);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Student> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Student> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Student> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Student> entities);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 变更学生状态
     * 0-离校；1-在校
     * @param id 学号
     * @param status 状态
     * @return
     */
    int changeStatusById(@Param("id") String id,@Param("status")Integer status);

    /**
     * 通过昵称查询单条数据
     *
     * @param nickname 昵称
     * @return 实例对象
     */
    Student queryByNickname(String nickname);

    /**
     * 通过实体作为筛选条件，分页查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryByParamLimit(@Param("student")Student student, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据条件查询学生记录总数
     *
     * @return 学生记录总数
     */
    Integer queryCount(@Param("student")Student student);

}
