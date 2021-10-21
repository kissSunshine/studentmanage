package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.StudentService;
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

    @PostMapping("/login")
    public ResponseVo login(@RequestBody User user){

        if ("Stu".equals(user.getUserType())) {
            return studentService.login(user);
        } else if ("Tea".equals(user.getUserType())){
            return ResponseVo.success("教师登录");
        }

        return null;
    }

}
