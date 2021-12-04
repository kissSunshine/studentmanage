package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ClassRealTeacherMapper;
import com.zh.studentmanage.enums.ErrorEnum;
import com.zh.studentmanage.exception.CustomException;
import com.zh.studentmanage.pojo.ClassRealTeacher;
import com.zh.studentmanage.service.ClassRealTeacherService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("classRealTeacherService")
public class ClassRealTeacherServiceImpl implements ClassRealTeacherService {
    @Resource
    private ClassRealTeacherMapper classRealTeacherMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ClassRealTeacher queryById(String id) {
        return this.classRealTeacherMapper.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param classRealTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(ClassRealTeacher classRealTeacher) {
        int insertCount = classRealTeacherMapper.insert(classRealTeacher);
        if (insertCount != 1) {
            throw new CustomException(ErrorEnum.CLA_R_TEA_ADD_FAIL);
        }
    }

    /**
     * 修改数据
     *
     * @param classRealTeacher 实例对象
     * @return 实例对象
     */
    @Override
    public ClassRealTeacher update(ClassRealTeacher classRealTeacher) {
        this.classRealTeacherMapper.update(classRealTeacher);
        return this.queryById(classRealTeacher.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param classesId 主键
     * @return 是否成功
     */
    @Override
    public void deleteByClassesId(String classesId) {
        int deleteCount = classRealTeacherMapper.deleteByClassesId(classesId);
        // 每个班都有语数外三名教师
        if (deleteCount != 3) {
            throw new CustomException(ErrorEnum.CLA_R_TEA_DELETE_FAIL);
        }
    }

    @Override
    public List<ClassRealTeacher> queryByClassIdBatch(List<String> classesidList) {
        List<ClassRealTeacher> classRealTeacherList = classRealTeacherMapper.queryByClassIdBatch(classesidList);
        if (classRealTeacherList.size() == 0) {
            throw new CustomException(ErrorEnum.CLA_R_TEA_NOT_FOUND);
        }
        return classRealTeacherList;
    }

    @Override
    public int haveClassTeacher(String classId) {
        return classRealTeacherMapper.haveClassTeacher(classId);
    }


}
