package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.StudentMapper;
import com.zh.studentmanage.enums.*;
import com.zh.studentmanage.excelexport.StudentExport;
import com.zh.studentmanage.exception.CustomException;
import com.zh.studentmanage.pojo.Student;
import com.zh.studentmanage.pojo.User;
import com.zh.studentmanage.service.StudentService;
import com.zh.studentmanage.utils.TimeUtil;
import com.zh.studentmanage.vo.PageVo;
import com.zh.studentmanage.vo.ResponseVo;
import com.zh.studentmanage.vo.StudentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private TimeUtil timeUtil;

    @Override
    public Student queryById(String id) {
        return null;
    }

    @Override
    public ResponseVo<String> insert(Student student) {
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
        //student.setBirthday(student.getBirthday().substring(0,10));
        // 如果身份证号为空，

        // 5、插入数据库
        int insertCount = studentMapper.insert(student);
        if(insertCount == 0){
            return ResponseVo.error("添加学生信息失败！");
        }

        return ResponseVo.success("添加成功！");
    }

    @Override
    public ResponseVo<String> update(Student student) {
        // 1、转换生日字段类型
        //student.setBirthday(student.getBirthday().substring(0,10));
        // 2、更新数据
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
    public ResponseVo<String> changeStatusById(String id, Integer status) {
        int changeCount = studentMapper.changeStatusById(id, status);
        if(changeCount == 0){
            return ResponseVo.error("变更学生信息失败！");
        }

        return ResponseVo.success("变更成功！");
    }

    @Override
    public ResponseVo<Student> queryForPage(Student student, int currentPage, int pageSize) {
        Map<String, Object> studentMap = queryByParamLimit(student, currentPage, pageSize);
        List<Student> studentList = (List<Student>) studentMap.get(PageEnum.DATA.getCode());

        // 4、封装VO对象
        List<StudentVo> studentVoList = new ArrayList<>();
        for (Student stu : studentList) {
            // 先将已有数据封装到VO
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(stu, studentVo);
            // 生日由Date类型转为String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(stu.getBirthday());
            studentVo.setBirthday(date);
            // 再封装状态值对应的汉字
            // 性别
            studentVo.setGenderName(studentVo.getGenderEnum().getGender());
            // 状态
            studentVo.setStatusName(studentVo.getStatusEnum().getName());
            // 校区
            studentVo.setSchoolName(studentVo.getSchoolEnum().getName());

            studentVoList.add(studentVo);
        }
        // 5.封装分页数据
        PageVo<List<StudentVo>> studentListPageVo = new PageVo<>(studentVoList, (int)studentMap.get(PageEnum.TOTAL.getCode()));

        return ResponseVo.success("查询成功", studentListPageVo);
    }

    @Override
    public Map<String, Object> queryByParamLimit(Student student, Integer currentPage, Integer pageSize) {
        // 1、查询学生记录总数
        int studentCount = studentMapper.queryCount(student);
        if (studentCount == 0) {
            throw new CustomException(ErrorEnum.STUDENT_NO_ONE);
        }

        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        Integer offset = null;
        if (currentPage != null && pageSize != null) {
            offset = (currentPage - 1) * pageSize;
        }
        // 3、查询
        List<Student> studentList = studentMapper.queryByParamLimit(student, offset, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put(PageEnum.TOTAL.getCode(), studentCount);
        map.put(PageEnum.DATA.getCode(), studentList);
        return map;
    }

    @Override
    public ResponseVo<Student> login(User user) {
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

    @Override
    public List<StudentExport> export(Student student) {
        Map<String, Object> studentMap = queryByParamLimit(student, null, null);
        List<Student> studentList = (List<Student>) studentMap.get(PageEnum.DATA.getCode());
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
        return studentExportList;
    }

    @Override
    public ResponseVo<String> importStudents(List<StudentExport> studentExportList) {
        // 1、校验数据非空、是否按规则填写
        for (StudentExport studentExport : studentExportList) {
            if (null == studentExport.getName()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_NAME_EMPTY);
            }
            if (studentExport.getName().length() > 20) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_NAME_LENGTH.getCode(),(studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_NAME_LENGTH.getMessage())));
            }

            if (null == studentExport.getNickname()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_NICKNAME_EMPTY);
            }
            if (studentExport.getNickname().length() > 20) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_NICKNAME_LENGTH.getCode(),(studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_NICKNAME_LENGTH.getMessage())));
            }

            if (null == studentExport.getBirthday()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_BIRTHDAY_EMPTY.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_BIRTHDAY_EMPTY.getMessage())));
            }
            Integer diffDay = timeUtil.dateCompareNow(studentExport.getBirthday());
            if (null == diffDay || diffDay < 0) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_BIRTHDAY_ERROR.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_BIRTHDAY_ERROR.getMessage())));
            }

            if (null == studentExport.getGenderName()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_GENDER_EMPTY.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_GENDER_EMPTY.getMessage())));
            }
            if (null == studentExport.getSchoolName()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_SCHOOL_EMPTY.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_SCHOOL_EMPTY.getMessage())));
            }
            if (null == studentExport.getPhone()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_PHONE_EMPTY.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_PHONE_EMPTY.getMessage())));
            }
            if (null == studentExport.getStatusName()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_STATUS_EMPTY.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_STATUS_EMPTY.getMessage())));
            }
        }
        // 2、校验昵称唯一
        List<String> nicknameList = studentExportList.stream().map(StudentExport::getNickname).collect(Collectors.toList());
        List<Student> resultList = studentMapper.queryByNicknameList(nicknameList);
        if (resultList.size() > 0) {
            String nicknames = resultList.stream().map(Student::getNickname).collect(Collectors.joining(",","[","]"));
            throw new CustomException(ErrorEnum.STUDENT_IMPORT_NICKNAME_AGAIN.getCode(), (nicknames.concat("：").concat(ErrorEnum.STUDENT_IMPORT_NICKNAME_AGAIN.getMessage())));
        }

        // 3、StudentExport转为Student
        List<Student> studentList = new ArrayList<>();
        for (StudentExport studentExport : studentExportList) {
            Student student = new Student();
            BeanUtils.copyProperties(studentExport, student);
            // 性别转为编码
            for (GenderEnum genderEnum : GenderEnum.values()) {
                if (studentExport.getGenderName().equals(genderEnum.getGender())) {
                    student.setGender(genderEnum.getCode());
                    break;
                }
            }
            if (null == student.getGender()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_GENDER_ERROR.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_GENDER_ERROR.getMessage())));
            }
            // 校区转为编码
            for (SchoolEnum schoolEnum : SchoolEnum.values()) {
                if (studentExport.getSchoolName().equals(schoolEnum.getName())) {
                    student.setSchoolid(schoolEnum.getCode());
                    break;
                }
            }
            if (null == student.getSchoolid()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_SCHOOL_ERROR.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_SCHOOL_ERROR.getMessage())));
            }
            // 状态转为编码
            for (StatusEnum statusEnum : StatusEnum.values()) {
                if (studentExport.getStatusName().equals(statusEnum.getName())) {
                    student.setStatus(statusEnum.getCode());
                    break;
                }
            }
            if (null == student.getStatus()) {
                throw new CustomException(ErrorEnum.STUDENT_IMPORT_STATUS_ERROR.getCode(), (studentExport.getName().concat("：").concat(ErrorEnum.STUDENT_IMPORT_STATUS_ERROR.getMessage())));
            }
            // 初始密码为手机号
            student.setPassword(DigestUtils.md5DigestAsHex(studentExport.getPhone().getBytes(StandardCharsets.UTF_8)));

            // 生成UUID作为主键
            String id = "Stu" + UUID.randomUUID().toString().replace("-", "");
            student.setId(id);
            studentList.add(student);
        }

        int count = studentMapper.insertBatch(studentList);
        if (count != studentList.size()) {
            throw new CustomException(ErrorEnum.STUDENT_IMPORT_ERROR);
        }

        return ResponseVo.success("导入成功");
    }
}
