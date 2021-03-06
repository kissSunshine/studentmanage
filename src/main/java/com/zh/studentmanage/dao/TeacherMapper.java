package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ActivityRealAddress;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Teacher)表数据库访问层
 *
 * @author makejava
 * @since 2021-10-25 14:37:22
 */
public interface TeacherMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teacher queryById(String id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param teacher 实例对象
     * @return 对象列表
     */
    List<Teacher> queryByParam(Teacher teacher);

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int insert(Teacher teacher);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Teacher> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Teacher> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Teacher> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Teacher> entities);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 变更教师状态
     * 0-离校；1-在校
     * @param id 教师编号
     * @param status 状态
     * @return 影响行数
     */
    int changeStatusById(@Param("id") String id,@Param("status")Integer status);

    /**
     * 通过昵称查询单条数据
     *
     * @param nickname 昵称
     * @return 实例对象
     */
    Teacher queryByNickname(String nickname);

    /**
     * 根据条件查询教师记录总数
     *
     * @return 教师记录总数
     */
    Integer queryCount(Teacher teacher);

    /**
     * 通过实体作为筛选条件，分页查询
     *
     * @param teacher 实例对象
     * @return 对象列表
     */
    List<Teacher> queryByParamLimit(@Param("teacher")Teacher teacher, @Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     * 通过ID批量查询数据
     *
     * @param teacherIdList 主键列表
     * @return 实例对象
     */
    List<Teacher> queryByIdBatch(@Param("teacherIdList") List<String> teacherIdList);

}

