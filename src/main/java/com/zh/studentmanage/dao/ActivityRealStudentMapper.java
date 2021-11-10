package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ActivityRealStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (ActivityRealStudent)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-10 15:43:46
 */
public interface ActivityRealStudentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    ActivityRealStudent queryById(String activityid);

    /**
     * 查询指定行数据
     *
     * @param activityRealStudent 查询条件
     * @return 对象列表
     */
    List<ActivityRealStudent> queryAllByLimit(ActivityRealStudent activityRealStudent);

    /**
     * 统计总行数
     *
     * @param activityRealStudent 查询条件
     * @return 总行数
     */
    long count(ActivityRealStudent activityRealStudent);

    /**
     * 新增数据
     *
     * @param activityRealStudent 实例对象
     * @return 影响行数
     */
    int insert(ActivityRealStudent activityRealStudent);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealStudent> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityRealStudent> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealStudent> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityRealStudent> entities);

    /**
     * 修改数据
     *
     * @param activityRealStudent 实例对象
     * @return 影响行数
     */
    int update(ActivityRealStudent activityRealStudent);

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 影响行数
     */
    int deleteById(String activityid);

}

