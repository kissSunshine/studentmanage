package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.vo.PageVo;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student queryById(String id) {
        return null;
    }

    @Override
    public ResponseVo insert(Student student) {
        // 1、非空校验放在Controller中

        // 2、唯一校验
        Student studentCheck = studentMapper.queryByNickname(student.getNickname());
        if(studentCheck != null){
            return ResponseVo.error("昵称已注册，请修改！");
        }

        // 3、密码转为MD5加密
        //MD5摘要算法(Spring自带)
        student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes(StandardCharsets.UTF_8)));

        // 4、填充其余信息
        // 生成UUID作为主键
        String id = "Stu" + UUID.randomUUID().toString().replace("-", "");
        student.setId(id);
        // 转换生日字段类型
        student.setBirthday(student.getBirthday().substring(0,10));
        // 如果身份证号为空，

        // 5、插入数据库
        int insertCount = studentMapper.insert(student);
        if(insertCount == 0){
            return ResponseVo.error("添加学生信息失败！");
        }

        return ResponseVo.success("添加成功！");
    }

    @Override
    public ResponseVo update(Student student) {
        int updateCount = studentMapper.update(student);
        if(updateCount == 0){
            return ResponseVo.error("更新学生信息失败！");
        }

        return ResponseVo.success("更新成功！");
    }

    @Override
    public ResponseVo<String> deleteById(String id) {
        int deleteCount = studentMapper.deleteById(id);
        if(deleteCount == 0){
            return ResponseVo.error("删除学生信息失败！");
        }

        return ResponseVo.success("删除成功！");
    }

    @Override
    public ResponseVo changeStatusById(String id, Integer status) {
        int changeCount = studentMapper.changeStatusById(id, status);
        if(changeCount == 0){
            return ResponseVo.error("变更学生信息失败！");
        }

        return ResponseVo.success("变更成功！");
    }

    @Override
    public ResponseVo<Student> queryByParam(Student student, int currentPage, int pageSize) {
        // 1、查询学生记录总数
        Integer studentCount = studentMapper.queryCount(student);
        if (studentCount == null) {
            return ResponseVo.error("查询学生信息失败！");
        }
        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        int offset = (currentPage - 1) * pageSize;
        // 3、查询
        List<Student> studentList = studentMapper.queryByParamLimit(student, offset, pageSize);
        if (studentList == null) {
            return ResponseVo.error("查询学生信息失败！");
        }
        // 4.封装分页数据
        PageVo<List<Student>> studentListPageVo = new PageVo<>(studentList, studentCount);


        return ResponseVo.success("查询成功", studentListPageVo);
    }

    @Override
    public ResponseVo login(User user) {
        Student student = studentMapper.queryByNickname(user.getNickname());
        if(student == null){
            return ResponseVo.error("用户名或密码错误！");
        }

        //将输入密码转为MD5格式
        String passwordMD5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        if(!student.getPassword().equals(passwordMD5)){
            return ResponseVo.error("用户名或密码错误！");
        }

        return ResponseVo.success("登录成功！",student);
    }


}
