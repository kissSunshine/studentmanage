package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ClassesMapper;
import com.zh.studentmanage.pojo.Classes;
import com.zh.studentmanage.service.ClassesService;
import com.zh.studentmanage.vo.ClassesVo;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {
    @Resource
    private ClassesMapper classesMapper;

    @Override
    public ResponseVo<List<ClassesVo>> queryByParamLimit(Classes classes, int currentPage, int pageSize) {
        // 1、查询班级记录总数
        Integer classesCount = classesMapper.queryCount(classes);
        if (classesCount == null) {
            throw new RuntimeException("查询班级失败");
        }
        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        int offset = (currentPage - 1) * pageSize;
        List<Classes> classesList = classesMapper.queryByParamLimit(classes, offset, pageSize);
        // 3、封装班级主要信息
        List<ClassesVo> classesVoList = new ArrayList<>();
        for (Classes one : classesList) {
            ClassesVo classesVo = new ClassesVo();
            BeanUtils.copyProperties(one,classesVo);
        }

        return null;
    }
}
