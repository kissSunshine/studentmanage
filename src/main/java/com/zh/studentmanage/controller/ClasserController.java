package com.zh.studentmanage.controller;

import com.zh.studentmanage.form.ClassesForm;
import com.zh.studentmanage.pojo.Classes;
import com.zh.studentmanage.service.ClassesService;
import com.zh.studentmanage.vo.ClassesVo;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasserController {

    @Resource
    private ClassesService classesService;

    @GetMapping("/query")
    public ResponseVo<List<ClassesVo>> query(Classes classes, int currentPage, int pageSize) {
        return classesService.queryByParamLimit(classes, currentPage, pageSize);
    }

    @PostMapping("/add")
    public ResponseVo<String> add(@RequestBody ClassesForm classesForm) {
        return classesService.add(classesForm);
    }

    @PostMapping("/update")
    public ResponseVo<String> update(@RequestBody ClassesForm classesForm) {
        return classesService.update(classesForm);
    }

    @PostMapping("/delete")
    public ResponseVo<String> delete(@RequestBody ClassesForm classesForm) {
        return classesService.delete(classesForm);
    }
}
