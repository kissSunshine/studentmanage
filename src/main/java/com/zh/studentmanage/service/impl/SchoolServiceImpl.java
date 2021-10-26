package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.SchoolMapper;
import com.zh.studentmanage.pojo.School;
import com.zh.studentmanage.service.SchoolService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {
    @Resource
    private SchoolMapper schoolMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public School queryById(String id) {
        return this.schoolMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<School> queryAllByLimit(int offset, int limit) {
        return this.schoolMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    @Override
    public School insert(School school) {
        this.schoolMapper.insert(school);
        return school;
    }

    /**
     * 修改数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    @Override
    public School update(School school) {
        this.schoolMapper.update(school);
        return this.queryById(school.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.schoolMapper.deleteById(id) > 0;
    }

    /**
     * 查询所有校区
     * @return 校区列表
     */
    public ResponseVo<School> queryAll(){
        School school = new School();
        List<School> schoolList = schoolMapper.queryByParam(school);
        return ResponseVo.success("查询校区成功！",schoolList);
    }


}