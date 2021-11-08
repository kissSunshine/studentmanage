package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.TeacherMapper;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.Teacher;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.PageVo;
import com.zh.studentmanage.vo.ResponseVo;
import com.zh.studentmanage.vo.StudentVo;
import com.zh.studentmanage.vo.TeacherVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher queryById(String id) {

        return null;
    }

    @Override
    public ResponseVo<String> insert(Teacher teacher) {
        //1、非空校验放在Controller中

        //2、唯一校验
        Teacher teacherCheck = teacherMapper.queryByNickname(teacher.getNickname());
        if(teacherCheck != null){
            return ResponseVo.error("昵称已注册，请修改！");
        }

        //3、密码转为MD5加密
        //MD5摘要算法(Spring自带)
        teacher.setPassword(DigestUtils.md5DigestAsHex(teacher.getPassword().getBytes(StandardCharsets.UTF_8)));

        // 4、填充其余信息
        // 生成UUID作为主键
        String id = "Tea" + UUID.randomUUID().toString().replace("-", "");
        teacher.setId(id);

        // 5、插入数据库
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
    public ResponseVo queryByParamLimit(Teacher teacher, int currentPage, int pageSize) {
        // 1、查询老师记录总数
        Integer teacherCount = teacherMapper.queryCount(teacher);
        if (teacherCount == null) {
            return ResponseVo.error("查询老师信息失败！");
        }
        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        int offset = (currentPage - 1) * pageSize;
        // 3、查询
        List<Teacher> teacherList = teacherMapper.queryByParamLimit(teacher, offset, pageSize);
        if (teacherList == null) {
            return ResponseVo.error("查询老师信息失败！");
        }
        // 4、封装VO对象
        List<TeacherVo> teacherVoList = new ArrayList<>();
        for (Teacher tea : teacherList) {
            // 先将已有数据封装到VO
            TeacherVo teacherVo = new TeacherVo();
            BeanUtils.copyProperties(tea, teacherVo);

            // 再封装状态值对应的汉字
            // 性别
            teacherVo.setGenderName(teacherVo.getGenderEnum().getGender());
            // 校区
            teacherVo.setSchoolName(teacherVo.getSchoolEnum().getName());
            // 学科
            teacherVo.setSubjectName(teacherVo.getSubjectEnum().getName());
            // 部门
            teacherVo.setDepartmentName(teacherVo.getDepartmentEnum().getName());
            // 职位
            teacherVo.setPositionName(teacherVo.getPositionEnum().getName());
            // 状态
            teacherVo.setStatusName(teacherVo.getStatusEnum().getName());

            teacherVoList.add(teacherVo);
        }
        // 5.封装分页数据
        PageVo<List<TeacherVo>> teacherListPageVo = new PageVo<>(teacherVoList, teacherCount);

        return ResponseVo.success("查询成功", teacherListPageVo);
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
