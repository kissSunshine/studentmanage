package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.TeacherMapper;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher queryById(String id) {

        return null;
    }

    @Override
    public ResponseVo insert(Teacher teacher) {
        //1、非空校验放在Controller中

        //2、唯一校验
        Teacher teacherCheck = teacherMapper.queryByNickname(teacher.getNickname());
        if(teacherCheck != null){
            return ResponseVo.error("昵称已注册，请修改！");
        }

        //3、密码转为MD5加密
        //MD5摘要算法(Spring自带)
        teacher.setPassword(DigestUtils.md5DigestAsHex(teacher.getPassword().getBytes(StandardCharsets.UTF_8)));

        int insertCount = teacherMapper.insert(teacher);
        if(insertCount == 0){
            return ResponseVo.error("添加教师信息失败！");
        }

        return ResponseVo.success("添加成功！");
    }

    @Override
    public ResponseVo update(Teacher teacher) {
        int updateCount = teacherMapper.update(teacher);
        if(updateCount == 0){
            return ResponseVo.error("更新教师信息失败！");
        }
        return ResponseVo.success("更新成功！");
    }

    @Override
    public ResponseVo deleteById(String id) {
        int deleteCount = teacherMapper.deleteById(id);
        if(deleteCount == 0){
            return ResponseVo.error("删除教师信息失败！");
        }

        return ResponseVo.success("删除成功！");
    }

    @Override
    public ResponseVo changeStatusById(String id, Integer status) {
        int changeCount = teacherMapper.changeStatusById(id, status);
        if(changeCount == 0){
            return ResponseVo.error("变更教师信息失败！");
        }

        return ResponseVo.success("变更成功！");
    }

    @Override
    public ResponseVo queryByParam(Teacher teacher) {
        List<Teacher> teacherList = teacherMapper.queryByParam(teacher);
        if (teacherList == null) {
            return ResponseVo.error("查询教师信息失败！");
        }

        return ResponseVo.success("查询成功",teacherList);
    }

    @Override
    public ResponseVo login(User user) {
        Teacher teacher = teacherMapper.queryByNickname(user.getNickname());
        if(teacher == null){
            return ResponseVo.error("用户名或密码错误！");
        }

        //将输入密码转为MD5格式
        String passwordMD5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        if(!teacher.getPassword().equals(passwordMD5)){
            return ResponseVo.error("用户名或密码错误！");
        }

        return ResponseVo.success("登录成功！",teacher);
    }


}
