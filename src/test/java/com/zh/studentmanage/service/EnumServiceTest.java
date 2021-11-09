package com.zh.studentmanage.service;

import com.zh.studentmanage.StudentmanageApplicationTests;
import com.zh.studentmanage.enums.DepartmentEnum;
import com.zh.studentmanage.vo.ResponseVo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.Assert.*;

public class EnumServiceTest extends StudentmanageApplicationTests {

    @Resource
    private EnumService enumService;

    @Test
    public void enumToListMap() {

        ResponseVo<Map<String, String>> mapResponseVo = enumService.enumToListMap(DepartmentEnum.values());
        System.out.println(mapResponseVo);

        for (DepartmentEnum e :
                DepartmentEnum.values()) {
            String code = e.getCode();
            String name = e.getName();
        }
    }
}