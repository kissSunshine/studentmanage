package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody User user){

        if ("Stu".equals(user.getUserType())) {
            return studentService.login(user);
        } else if ("Tea".equals(user.getUserType())){
            return teacherService.login(user);
        }

        return ResponseVo.error("请选择登录用户类型！");
    }

}
