package com.zh.studentmanage.controller;

import com.zh.studentmanage.enums.DepartmentEnum;
import com.zh.studentmanage.enums.PositionEnum;
import com.zh.studentmanage.enums.SubjectEnum;
import com.zh.studentmanage.service.EnumService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @Resource
    private EnumService enumService;

    @GetMapping("/department")
    public ResponseVo<List<Map<String, String>>> department(){
        return enumService.enumToListMap(DepartmentEnum.values());
    }

    @GetMapping("/position")
    public ResponseVo<List<Map<String, String>>> position(){
        return enumService.enumToListMap(PositionEnum.values());
    }

    @GetMapping("/subject")
    public ResponseVo<List<Map<String, String>>> subject(){
        return enumService.enumToListMap(SubjectEnum.values());
    }

}
