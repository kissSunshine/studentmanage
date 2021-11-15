package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.School;
import com.zh.studentmanage.service.SchoolService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @GetMapping("/queryAll")
    public ResponseVo<List<School>> queryAll() {
        return schoolService.queryAll();
    }

}
