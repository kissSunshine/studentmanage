package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.School;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;

public interface SchoolService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    School queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<School> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    ResponseVo<String> insert(School school);

    /**
     * 修改数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    ResponseVo<String> update(School school);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResponseVo<String> deleteById(String id);

    /**
     * 查询所有校区
     * @return 校区列表
     */
    ResponseVo<List<School>> queryAll();
}
