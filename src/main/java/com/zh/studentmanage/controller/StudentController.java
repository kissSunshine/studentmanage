package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseVo add(@RequestBody Student student){
        return studentService.insert(student);
    }

    @PostMapping("/query")
    public ResponseVo query(@RequestBody Student student) {
        return studentService.queryByParam(student);
    }

}
