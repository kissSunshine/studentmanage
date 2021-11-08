package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;

/**
 * (Teacher)表服务接口
 *
 * @author makejava
 * @since 2021-10-25 14:37:22
 */
public interface TeacherService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Teacher queryById(String id);

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    ResponseVo insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    ResponseVo update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResponseVo deleteById(String id);

    /**
     * 变更教师状态
     * 0-离校；1-在校
     * @param id 教师编号
     * @param status 状态
     * @return
     */
    ResponseVo changeStatusById(String id,Integer status);

    /**
     * 通过重要因素查询
     * @param teacher 学生信息
     * @return
     */
    ResponseVo queryByParamLimit(Teacher teacher, int currentPage, int pageSize);

    /**
     * 登录
     * @param user 用户
     * @return
     */
    ResponseVo login(User user);

}
