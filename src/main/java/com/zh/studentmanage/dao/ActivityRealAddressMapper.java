package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.ActivityRealAddress;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ActivityRealAddressMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param activityid 主键
     * @return 实例对象
     */
    ActivityRealAddress queryById(String activityid);

    /**
     * 通过实体作为筛选条件分页查询
     *
     * @param activityRealAddress 查询条件
     * @return 对象列表
     */
    List<ActivityRealAddress> queryByParamLimit(@Param("activityRealAddress") ActivityRealAddress activityRealAddress, @Param("offset") int offset, @Param("pageSize") int limit);

    /**
     * 统计总行数
     *
     * @param activityRealAddress 查询条件
     * @return 总行数
     */
    long count(ActivityRealAddress activityRealAddress);

    /**
     * 新增数据
     *
     * @param activityRealAddress 实例对象
     * @return 影响行数
     */
    int insert(ActivityRealAddress activityRealAddress);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealAddress> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ActivityRealAddress> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ActivityRealAddress> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ActivityRealAddress> entities);

    /**
     * 修改数据
     *
     * @param activityRealAddress 实例对象
     * @return 影响行数
     */
    int update(ActivityRealAddress activityRealAddress);

    /**
     * 通过主键删除数据
     *
     * @param activityid 主键
     * @return 影响行数
     */
    int deleteById(String activityid);

    /**
     * 通过ID批量查询数据
     *
     * @param activityidList 主键列表
     * @return 实例对象
     */
    List<ActivityRealAddress> queryByIdBatch(@Param("activityidList") List<String> activityidList);

}

