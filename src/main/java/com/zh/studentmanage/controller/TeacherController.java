package com.zh.studentmanage.controller;

import com.zh.studentmanage.enums.PositionEnum;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/query")
    public ResponseVo<Teacher> query(Teacher teacher, int currentPage, int pageSize) {
        return teacherService.queryByParamLimit(teacher, currentPage, pageSize);
    }

    @PostMapping("/add")
    public ResponseVo<String> add(@RequestBody Teacher teacher) {
        return teacherService.insert(teacher);
    }

    @PostMapping("/update")
    public ResponseVo<String> update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @PostMapping("/delete")
    public ResponseVo<String> delete(@RequestBody Teacher teacher) {
        return teacherService.deleteById(teacher.getId());
    }

    @GetMapping("/queryByParams")
    public ResponseVo<List<Teacher>> queryByParams(Teacher teacher) {
        List<Teacher> teacherList = teacherService.queryByParam(teacher);
        return ResponseVo.success("查询成功", teacherList);
    }

    @GetMapping("/querySubjectTeacher")
    public ResponseVo<List<String[]>> querySubjectTeacher() {
        // 查询学科教师，职位为班主任和教师
        Teacher teacher = new Teacher();
        // 班主任
        teacher.setPosition(PositionEnum.CLASSES_DIRECTOR.getCode());
        List<Teacher> directorList = teacherService.queryByParam(teacher);
        // 教师
        teacher.setPosition(PositionEnum.TEACHER.getCode());
        List<Teacher> teacherList = teacherService.queryByParam(teacher);
        // 合并
        directorList.addAll(teacherList);

        List<String[]> subjectTeacherList = directorList.stream().map(one -> {
            return new String[]{one.getId(), one.getNickname(), one.getSubject()};
        }).collect(Collectors.toList());

        return ResponseVo.success("查询成功", subjectTeacherList);
    }

}
