package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseVo add(@RequestBody Student student){
        return studentService.insert(student);
    }

    @GetMapping("/query")
    public ResponseVo<Student> query(Student student, int currentPage, int pageSize) {
        return studentService.queryByParam(student, currentPage, pageSize);
    }

    @PostMapping("/delete")
    public ResponseVo<String> delete(@RequestBody Student student){
        return studentService.deleteById(student.getId());
    }

    @PostMapping("/update")
    public ResponseVo<String> update(@RequestBody Student student){
        return studentService.update(student);
    }

}
