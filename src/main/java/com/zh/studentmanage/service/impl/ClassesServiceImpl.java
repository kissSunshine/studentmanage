package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ClassesMapper;
import com.zh.studentmanage.enums.ErrorEnum;
import com.zh.studentmanage.enums.SubjectEnum;
import com.zh.studentmanage.exception.CustomException;
import com.zh.studentmanage.form.ClassesForm;
import com.zh.studentmanage.pojo.ClassRealTeacher;
import com.zh.studentmanage.pojo.Classes;
import com.zh.studentmanage.service.*;
import com.zh.studentmanage.vo.ClassesVo;
import com.zh.studentmanage.vo.PageVo;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private TeacherService teacherService;
    @Resource
    private ClassRealTeacherService classRealTeacherService;
    @Resource
    private ClassRealStudentService classRealStudentService;

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

        // 4、为封装教师信息做准备
        // 查询班级老师
        List<String> classesIdList = classesList.stream().map(Classes::getId).collect(Collectors.toList());
        List<ClassRealTeacher> classRealTeacherList = classRealTeacherList = classRealTeacherService.queryByClassIdBatch(classesIdList);

        // 5、学生人数
        Map<String, Integer> studentCountMap = new HashMap<>();
        for (String classesId : classesIdList) {
            int count = classRealStudentService.queryCountByClassesId(classesId);
            studentCountMap.put(classesId, count);
        }

        // 6、封装班级信息和班级教师信息
        List<ClassesVo> classesVoList = new ArrayList<>();
        for (Classes one : classesList) {
            // 班级信息
            ClassesVo classesVo = new ClassesVo();
            BeanUtils.copyProperties(one,classesVo);
            // 班级教师信息
            for (ClassRealTeacher teacher : classRealTeacherList) {
                // 班级相同
                if (teacher.getClassid().equals(one.getId())) {
                    if (teacher.getSubject().equals(SubjectEnum.YUWEN.getCode())) {
                        classesVo.setYuwen(teacher.getTeacherid());
                        classesVo.setYuwenWeek(teacher.getWeek());
                        classesVo.setYuwenStartTime(teacher.getStarttime());
                        classesVo.setYuwenEndTime(teacher.getEndtime());
                    }
                    if (teacher.getSubject().equals(SubjectEnum.MATH.getCode())) {
                        classesVo.setMath(teacher.getTeacherid());
                        classesVo.setMathWeek(teacher.getWeek());
                        classesVo.setMathStartTime(teacher.getStarttime());
                        classesVo.setMathEndTime(teacher.getEndtime());
                    }
                    if (teacher.getSubject().equals(SubjectEnum.ENGLISH.getCode())) {
                        classesVo.setEnglish(teacher.getTeacherid());
                        classesVo.setEnglishWeek(teacher.getWeek());
                        classesVo.setEnglishStartTime(teacher.getStarttime());
                        classesVo.setEnglishEndTime(teacher.getEndtime());
                    }

                }
            }
            // 班级学生人数
            for (Map.Entry<String,Integer> map: studentCountMap.entrySet()) {
                if (classesVo.getId().equals(map.getKey())) {
                    classesVo.setStudentCount(map.getValue());
                    break;
                }
            }

            classesVoList.add(classesVo);
        }

        PageVo<List<ClassesVo>> pageVo = new PageVo<>(classesVoList,classesCount);
        return ResponseVo.success("查询成功！", pageVo);
    }

    @Override
    @Transactional
    public ResponseVo<String> add(ClassesForm classesForm) {
        // 1、添加班级
        Classes classes = new Classes();
        BeanUtils.copyProperties(classesForm, classes);
        // 查询班级名是否已经占用
        Classes classesForQuery = new Classes();
        classesForQuery.setName(classes.getName());
        classesForQuery.setSchoolid(classes.getSchoolid());
        List<Classes> classesList = classesMapper.queryByParamLimit(classesForQuery, 0, 1);
        if (classesList.size() != 0) {
            throw new CustomException(ErrorEnum.CLASSES_NAME_EXISTS);
        }

        // 生成UUID作为主键
        String classesId = "Cla" + UUID.randomUUID().toString().replace("-", "");
        classes.setId(classesId);

        // 新增
        int insertCount = classesMapper.insert(classes);
        if (insertCount == 0) {
            throw new CustomException(ErrorEnum.CLASSES_ADD_FAIL);
        }

        // 2、添加班级教师
        ClassRealTeacher yuwenTeacher = getSubjectTeacher(classesId, classesForm, SubjectEnum.YUWEN.getCode());
        classRealTeacherService.insert(yuwenTeacher);

        ClassRealTeacher mathTeacher = getSubjectTeacher(classesId, classesForm, SubjectEnum.MATH.getCode());
        classRealTeacherService.insert(mathTeacher);

        ClassRealTeacher englishTeacher = getSubjectTeacher(classesId, classesForm, SubjectEnum.ENGLISH.getCode());
        classRealTeacherService.insert(englishTeacher);

        return ResponseVo.success("添加班级成功");
    }

    /**
     * 根据ClassesForm获取ClassRealTeacher
     * @param classesId 班级id
     * @param classesForm 班级表单
     * @param subject 学科
     * @return 班级教师
     */
    private ClassRealTeacher getSubjectTeacher(String classesId, ClassesForm classesForm, String subject) {
        ClassRealTeacher classRealTeacher = new ClassRealTeacher();
        classRealTeacher.setClassid(classesId);

        String id = "CRT" + UUID.randomUUID().toString().replace("-", "");
        classRealTeacher.setId(id);

        classRealTeacher.setUpdatedPerson(classesForm.getUpdatedPerson());

        if (subject.equals(SubjectEnum.YUWEN.getCode())) {
            classRealTeacher.setSubject(SubjectEnum.YUWEN.getCode());
            classRealTeacher.setTeacherid(classesForm.getYuwen());
            classRealTeacher.setWeek(classesForm.getYuwenWeek());
            classRealTeacher.setStarttime(classesForm.getYuwenStartTime());
            classRealTeacher.setEndtime(classesForm.getYuwenEndTime());
        }
        if (subject.equals(SubjectEnum.MATH.getCode())) {
            classRealTeacher.setSubject(SubjectEnum.MATH.getCode());
            classRealTeacher.setTeacherid(classesForm.getMath());
            classRealTeacher.setWeek(classesForm.getMathWeek());
            classRealTeacher.setStarttime(classesForm.getMathStartTime());
            classRealTeacher.setEndtime(classesForm.getMathEndTime());
        }
        if (subject.equals(SubjectEnum.ENGLISH.getCode())) {
            classRealTeacher.setSubject(SubjectEnum.ENGLISH.getCode());
            classRealTeacher.setTeacherid(classesForm.getEnglish());
            classRealTeacher.setWeek(classesForm.getEnglishWeek());
            classRealTeacher.setStarttime(classesForm.getEnglishStartTime());
            classRealTeacher.setEndtime(classesForm.getEnglishEndTime());
        }
        return classRealTeacher;
    }

    @Override
    public ResponseVo<String> update(Classes classes) {
        // 查询班级名是否已经占用
        Classes classesForQuery = new Classes();
        classesForQuery.setName(classes.getName());
        List<Classes> classesList = classesMapper.queryByParamLimit(classesForQuery, 1, 1);
        if (classesList.size() != 0) {
            throw new CustomException(ErrorEnum.CLASSES_NAME_EXISTS);
        }

        // 修改
        int updateCount = classesMapper.update(classes);
        if (updateCount == 0) {
            throw new CustomException(ErrorEnum.CLASSES_UPDATE_FAIL);
        }
        return ResponseVo.success("修改班级信息成功");
    }

    @Override
    public ResponseVo<String> delete(ClassesForm classesForm) {
        // 删除班级前必须删除所有的学生
        int classStudentCount = classRealStudentService.haveClassStudent(classesForm.getId());
        if (classStudentCount == 1) {
            throw new CustomException(ErrorEnum.CLASSES_DELETE_HAVE_STUDENT);
        }
        // 删除班级教师
        classRealTeacherService.deleteByClassesId(classesForm.getId());
        // 删除班级
        int deleteCount = classesMapper.deleteById(classesForm.getId());
        if (deleteCount == 0) {
            throw new CustomException(ErrorEnum.CLASSES_DELETE_FAIL);
        }
        return ResponseVo.success("删除成功！");
    }

}
