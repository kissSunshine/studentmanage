package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/query")
    public ResponseVo<Teacher> query(Teacher teacher, int currentPage, int pageSize) {
        return teacherService.queryByParamLimit(teacher, currentPage, pageSize);
    }

}
