package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ClassesMapper;
import com.zh.studentmanage.enums.ErrorEnum;
import com.zh.studentmanage.enums.PositionEnum;
import com.zh.studentmanage.enums.SubjectEnum;
import com.zh.studentmanage.exception.CustomException;
import com.zh.studentmanage.pojo.Activity;
import com.zh.studentmanage.pojo.ClassRealTeacher;
import com.zh.studentmanage.pojo.Classes;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.service.ClassRealTeacherService;
import com.zh.studentmanage.service.ClassesService;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ClassesVo;
import com.zh.studentmanage.vo.ResponseVo;
import com.zh.studentmanage.vo.TeacherVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private TeacherService teacherService;
    @Resource
    private ClassRealTeacherService classRealTeacherService;

    @Override
    public ResponseVo<List<ClassesVo>> queryByParamLimit(Classes classes, int currentPage, int pageSize) {
        // 1、查询班级记录总数
        Integer classesCount = classesMapper.queryCount(classes);
        if (classesCount == 0) {
            throw new CustomException(ErrorEnum.CLASSES_NOT_FOUND);
        }
        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        int offset = (currentPage - 1) * pageSize;
        List<Classes> classesList = classesMapper.queryByParamLimit(classes, offset, pageSize);
        // 3、查询班级主要信息
        if (classesList.size() == 0) {
            throw new CustomException(ErrorEnum.CLASSES_NOT_FOUND);
        }
        // 4、查询职位为班主任的教师
        Teacher paramClassesMaster = new Teacher();
        paramClassesMaster.setPosition(PositionEnum.CLASSES_DIRECTOR.getCode());
        List<Teacher> classesmasterList = teacherService.queryByParam(paramClassesMaster);
        // 5、查询学科老师
        List<String> classesIdList = classesList.stream().map(Classes::getId).collect(Collectors.toList());
        List<ClassRealTeacher> classRealTeacherList = classRealTeacherService.queryByIdBatch(classesIdList);
        List<String> subjectTeacherIdList = classRealTeacherList.stream().map(ClassRealTeacher::getTeacherid).collect(Collectors.toList());
        List<Teacher> subjectTeacherList = teacherService.queryByIdBatch(subjectTeacherIdList);

        // 6、封装班级信息
        List<ClassesVo> classesVoList = new ArrayList<>();
        for (Classes one : classesList) {
            ClassesVo classesVo = new ClassesVo();
            BeanUtils.copyProperties(one,classesVo);
            // 获取班主任的名字
            for (Teacher master : classesmasterList) {
                if (master.getId().equals(one.getClassmaster())) {
                    classesVo.setClassmasterName(master.getNickname());
                }
            }
            // 获取学科老师名字
            for (Teacher subjectTeacher : subjectTeacherList) {
                if (subjectTeacher.getSubject().equals(SubjectEnum.YUWEN.getCode())) {
                    classesVo.setYuwenTeacher(subjectTeacher.getNickname());
                }
                if (subjectTeacher.getSubject().equals(SubjectEnum.MATH.getCode())) {
                    classesVo.setMathTeacher(subjectTeacher.getNickname());
                }
                if (subjectTeacher.getSubject().equals(SubjectEnum.ENGLISH.getCode())) {
                    classesVo.setEnglishTeacher(subjectTeacher.getNickname());
                }
            }
        }
        // 7、

        return null;
    }
}
