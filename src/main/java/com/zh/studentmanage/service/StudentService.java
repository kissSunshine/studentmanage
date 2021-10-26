package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.vo.ResponseVo;

/**
 * (Student)表服务接口
 *
 * @author makejava
 * @since 2021-10-19 23:28:50
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Student queryById(String id);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    ResponseVo insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    ResponseVo update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResponseVo deleteById(String id);

    /**
     * 变更学生状态
     * 0-离校；1-在校
     * @param id 学号
     * @param status 状态
     * @return
     */
    ResponseVo changeStatusById(String id,Integer status);

    /**
     * 通过重要因素查询
     *
     * @param student     学生信息
     * @param currentPage 当前页
     * @param pageSize    每页数据量
     * @return 学生数据
     */
    ResponseVo<Student> queryByParam(Student student, int currentPage, int pageSize);

    /**
     * 登录
     * @param user 用户
     * @return
     */
    ResponseVo login(User user);
}
