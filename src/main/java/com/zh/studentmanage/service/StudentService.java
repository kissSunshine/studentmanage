package com.zh.studentmanage.service;

import com.zh.studentmanage.excelexport.StudentExport;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;
import java.util.Map;

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
    ResponseVo<String> insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    ResponseVo<String> update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResponseVo<String> deleteById(String id);

    /**
     * 变更学生状态
     * 0-离校；1-在校
     * @param id 学号
     * @param status 状态
     * @return 返回提示信息
     */
    ResponseVo<String> changeStatusById(String id,Integer status);

    /**
     * 通过重要因素分页查询
     *
     * @param student     学生信息
     * @param currentPage 当前页
     * @param pageSize    每页数据量
     * @return 学生数据
     */
    ResponseVo<Student> queryForPage(Student student, int currentPage, int pageSize);

    /**
     * 通过重要因素分页查询
     *
     * @param student     学生信息
     * @param currentPage 当前页
     * @param pageSize    每页数据量
     * @return map,包含总数和查出的学生信息
     */
    Map<String, Object> queryByParamLimit(Student student, Integer currentPage, Integer pageSize);

    /**
     * 登录
     * @param user 用户
     * @return 返回登录的学生信息
     */
    ResponseVo<Student> login(User user);

    /**
     * 通过student条件查询，并导出所有数据
     * @param student 查询条件
     * @return 需要导出的学生数据
     */
    List<StudentExport> export(Student student);

    /**
     * 通过表格数据进行导入
     * @param  studentExportList  需要导入的数据
     * @return 导入结果
     */
    ResponseVo<String> importStudents(List<StudentExport> studentExportList);

}
