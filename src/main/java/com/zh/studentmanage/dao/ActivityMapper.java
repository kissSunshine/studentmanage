package com.zh.studentmanage.dao;

import com.zh.studentmanage.pojo.Activity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ActivityMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Activity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param activity 实例对象
     * @return 对象列表
     */
    List<Activity> queryAll(Activity activity);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int insert(Activity activity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Activity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Activity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Activity> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Activity> entities);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 影响行数
     */
    int update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 通过实体作为筛选条件分页查询
     *
     * @param activity 实例对象
     * @return 对象列表
     */
    List<Activity> queryByParamLimit(@Param("activity")Activity activity, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据条件查询活动总数
     *
     * @return 活动总数
     */
    Integer queryCount(Activity activity);

}

