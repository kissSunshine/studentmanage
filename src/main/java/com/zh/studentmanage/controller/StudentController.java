package com.zh.studentmanage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.zh.studentmanage.enums.PageEnum;
import com.zh.studentmanage.excelexport.StudentExport;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return studentService.queryForPage(student, currentPage, pageSize);
    }

    @PostMapping("/delete")
    public ResponseVo<String> delete(@RequestBody Student student){
        return studentService.deleteById(student.getId());
    }

    @PostMapping("/update")
    public ResponseVo<String> update(@RequestBody Student student){
        return studentService.update(student);
    }

    @GetMapping("/export")
    public void export(Student student, HttpServletResponse response) {
        Map<Enum, Object> studentMap = studentService.queryByParamLimit(student, null, null);
        List<Student> studentList = (List<Student>) studentMap.get(PageEnum.DATA);
        List<StudentExport> studentExportList = new ArrayList<>();
        for (Student one : studentList) {
            StudentExport studentExport = new StudentExport();
            BeanUtils.copyProperties(one, studentExport);

            // 性别
            studentExport.setGenderName(studentExport.getGenderEnum().getGender());
            // 状态
            studentExport.setStatusName(studentExport.getStatusEnum().getName());
            // 校区
            studentExport.setSchoolName(studentExport.getSchoolEnum().getName());

            studentExportList.add(studentExport);
        }

        ExportParams params = new ExportParams("学生表", "学生表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, StudentExport.class, studentExportList);
        ServletOutputStream out = null;
        try {
            // 流形式传输
            response.setHeader("content-type","application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
