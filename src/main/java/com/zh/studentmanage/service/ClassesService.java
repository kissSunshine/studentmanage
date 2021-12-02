package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.Classes;
import com.zh.studentmanage.vo.ClassesVo;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;


public interface ClassesService {

    /**
     * 通过重要因素查询
     * @param classes 班级信息
     * @return 结果
     */
    ResponseVo<List<ClassesVo>> queryByParamLimit(Classes classes, int currentPage, int pageSize);

    /**
     * 添加班级
     * @param classes 班级信息
     * @return 结果
     */
    ResponseVo<String> add(Classes classes);

    /**
     * 修改班级
     * @param classes 班级信息
     * @return 结果
     */
    ResponseVo<String> update(Classes classes);

    /**
     * 删除班级
     * @param classes 班级信息
     * @return 结果
     */
    ResponseVo<String> deleteClass(Classes classes);
}
