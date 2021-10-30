package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.Activity;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;

public interface ActivityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Activity queryById(String id);

    /**
     * 查询多条数据
     *
     * @param currentPage 查询起始位置
     * @param pageSize  查询条数
     * @return 对象列表
     */
    ResponseVo<List<Activity>> queryByParamLimit(Activity activity, int currentPage, int pageSize);

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity insert(Activity activity);

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    Activity update(Activity activity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
