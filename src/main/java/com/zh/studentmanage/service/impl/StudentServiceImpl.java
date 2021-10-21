package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
        //1、非空校验放在Controller中

        //2、唯一校验
        Student studentCheck = studentMapper.queryByNickname(student.getNickname());
        if(studentCheck != null){
            return ResponseVo.error("昵称已注册，请修改！");
        }

        //3、密码转为MD5加密
        //MD5摘要算法(Spring自带)
        student.setPassword(DigestUtils.md5DigestAsHex(student.getPassword().getBytes(StandardCharsets.UTF_8)));

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
    public ResponseVo deleteById(String id) {
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
    public ResponseVo queryByParam(Student student) {
        List<Student> studentList = studentMapper.queryByParam(student);
        if (studentList == null) {
            return ResponseVo.error("查询学生信息失败！");
        }

        return ResponseVo.success("查询成功",studentList);
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
