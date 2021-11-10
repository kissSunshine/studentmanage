package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ActivityRealTeacher;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ActivityRealTeacher)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-10 15:16:57
 */
public interface ActivityRealTeacherMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    ActivityRealTeacher queryById(String activityid);

    /**
     * 查询指定行数据
     *
     * @param activityRealTeacher 查询条件
     * @return 对象列表
     */
    List<ActivityRealTeacher> queryAllByLimit(ActivityRealTeacher activityRealTeacher);

    /**
     * 统计总行数
     *
     * @param activityRealTeacher 查询条件
     * @return 总行数
     */
    long count(ActivityRealTeacher activityRealTeacher);

    /**
     * 新增数据
     *
     * @param activityRealTeacher 实例对象
     * @return 影响行数
     */
    int insert(ActivityRealTeacher activityRealTeacher);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealTeacher> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityRealTeacher> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealTeacher> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityRealTeacher> entities);

    /**
     * 修改数据
     *
     * @param activityRealTeacher 实例对象
     * @return 影响行数
     */
    int update(ActivityRealTeacher activityRealTeacher);

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 影响行数
     */
    int deleteById(String activityid);

}

