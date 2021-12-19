package com.zh.studentmanage.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseVo add(@RequestBody Student student) {
        return studentService.insert(student);
    }

    @GetMapping("/query")
    public ResponseVo<Student> query(Student student, int currentPage, int pageSize) {
        return studentService.queryForPage(student, currentPage, pageSize);
    }

    @PostMapping("/delete")
    public ResponseVo<String> delete(@RequestBody Student student) {
        return studentService.deleteById(student.getId());
    }

    @PostMapping("/update")
    public ResponseVo<String> update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @GetMapping("/export")
    public ResponseVo<String> export(Student student, HttpServletResponse response) {
        // 查询出需要导出的数据
        List<StudentExport> studentExportList = studentService.export(student);

        // 表名  sheet名  excel类型
        ExportParams params = new ExportParams("学生表", "学生表", ExcelType.HSSF);
        // 参数  需要导出的类  数据list
        Workbook workbook = ExcelExportUtil.exportExcel(params, StudentExport.class, studentExportList);
        ServletOutputStream out = null;
        try {
            // 流形式传输
            response.setHeader("content-type", "application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls", "UTF-8"));
            // 将content-disposition暴露给前端，前端代码才可以拿到值
            response.setHeader("Access-Control-Expose-Headers", "content-disposition");
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ResponseVo.success("导出成功");
    }

    @PostMapping("/import")
    public ResponseVo<String> importStudents(@RequestParam("file")MultipartFile multipartFile) {
        ImportParams importParams = new ImportParams();
        // 标题行
        importParams.setTitleRows(1);
        try {
            List<StudentExport> studentExports = ExcelImportUtil.importExcel(multipartFile.getInputStream(), StudentExport.class, importParams);
            studentExports.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
