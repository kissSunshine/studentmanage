package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ActivityMapper;
import com.zh.studentmanage.dao.ActivityRealAddressMapper;
import com.zh.studentmanage.dao.ActivityRealTeacherMapper;
import com.zh.studentmanage.form.ActivityForm;
import com.zh.studentmanage.pojo.*;
import com.zh.studentmanage.service.ActivityRealAddressService;
import com.zh.studentmanage.service.ActivityService;
import com.zh.studentmanage.service.SchoolService;
import com.zh.studentmanage.service.TeacherService;
import com.zh.studentmanage.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivityRealAddressMapper activityRealAddressMapper;

    @Resource
    private ActivityRealAddressService activityRealAddressService;

    @Resource
    private ActivityRealTeacherMapper activityRealTeacherMapper;

    @Resource
    private SchoolService schoolService;

    @Resource
    private TeacherService teacherService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(String id) {
        return activityMapper.queryById(id);
    }

    /**
     * 根据条件分页查询
     * @param activity 活动实例
     * @param currentPage 查询起始位置
     * @param pageSize  查询条数
     * @return 对象列表
     */
    @Override
    public ResponseVo<List<Activity>> queryByParamLimit(Activity activity, int currentPage, int pageSize) {
        // 1、查询活动记录总数
        Integer activityCount = activityMapper.queryCount(activity);
        if (activityCount == null) {
            return ResponseVo.error("查询活动信息失败！");
        }
        // 2、计算 sql中 limit 的偏移数offset、查询记录数limit
        // 偏移数量 = 当前页码 × 每页显示数量
        int offset = (currentPage - 1) * pageSize;
        // 3、查询活动主信息
        List<Activity> activityList = activityMapper.queryByParamLimit(activity, offset, pageSize);
        if (activityList == null) {
            return ResponseVo.error("查询活动信息失败！");
        }
        if (activityList.size() == 0) {
            return ResponseVo.error("还没有添加过活动信息！");
        }

        // 4、先将活动的主要信息封装到VO对象
        List<ActivityVo> activityVoList = new ArrayList<>();
        for (Activity act : activityList) {
            // 先将已有数据封装到VO
            ActivityVo activityVo = new ActivityVo();
            BeanUtils.copyProperties(act, activityVo);
            // 活动主信息中的状态名称
            activityVo.setStatusName(activityVo.getStatusEnum().getName());

            activityVo.setActivityRealAddressVoList(new ArrayList<>());
            activityVo.setActivityRealTeacherVoList(new ArrayList<>());

            activityVoList.add(activityVo);
        }

        // 5、为封装activityRealAddressVoList和 准备数据
        // 查询所有校区
        List<School> schoolList = (List<School>) schoolService.queryAll().getData();

        // 6、查询活动地点信息并封装为activityRealAddressVoList
        ResponseVo araRV = getActivityRealAddressVoList(activityList, schoolList);
        if (araRV.getStatus().equals(0)) {
            return araRV;
        }
        List<ActivityRealAddressVo> activityRealAddressVoList = (List<ActivityRealAddressVo>) araRV.getData();

        // 7、查询活动教师信息
        List<ActivityRealTeacherVo> activityRealTeacherVoList = getActivityRealTeacherVoList(activityList, schoolList);

        // 8、再给活动封装   活动地点信息  和  活动教师信息
        for (ActivityVo av : activityVoList) {
            // 活动地点信息
            for (ActivityRealAddressVo arav : activityRealAddressVoList) {
                List<ActivityRealAddressVo> list;
                if (av.getId().equals(arav.getActivityid())) {
                    list = av.getActivityRealAddressVoList();
                } else {
                    list = new ArrayList<>();
                }
                list.add(arav);
                av.setActivityRealAddressVoList(list);
            }

            // 活动教师信息
            for (ActivityRealTeacherVo artv : activityRealTeacherVoList) {
                List<ActivityRealTeacherVo> list;
                if (av.getId().equals(artv.getActivityid())) {
                    list = av.getActivityRealTeacherVoList();
                } else {
                    list = new ArrayList<>();
                }
                list.add(artv);
                av.setActivityRealTeacherVoList(list);
            }
        }

        // 9、封装分页数据
        PageVo<List<ActivityVo>> activityListPageVo = new PageVo<>(activityVoList, activityCount);

        return ResponseVo.success("查询成功", activityListPageVo);
    }

    /**
     * 查询活动对应的活动地点信息，并封装为Vo
     * @param activityList 查询出的活动
     * @param schoolList 所有的校区
     * @return 活动地点信息VoList
     */
    private ResponseVo  getActivityRealAddressVoList(List<Activity> activityList, List<School> schoolList){
        // 将查询的活动的id取出拼接为list
        List<String> activityidList = activityList.stream().map(Activity::getId).collect(Collectors.toList());
        // 查询活动地点信息
        ResponseVo<List<ActivityRealAddress>> araRV = activityRealAddressService.queryByIdBatch(activityidList);
        if (araRV.getStatus().equals(0)) {
            return araRV;
        }
        List<ActivityRealAddress> activityRealAddressList = (List<ActivityRealAddress>) araRV.getData();
        // 封装
        List<ActivityRealAddressVo> activityRealAddressVoList = new ArrayList<>();
        for (ActivityRealAddress ara : activityRealAddressList) {
            ActivityRealAddressVo activityRealAddressVo = new ActivityRealAddressVo();
            // 先将已有数据封装到VO
            BeanUtils.copyProperties(ara, activityRealAddressVo);

            activityRealAddressVoList.add(activityRealAddressVo);
        }

        return ResponseVo.success("查询活动地址信息成功",activityRealAddressVoList);
    }

    /**
     * 查询活动对应的活动教师信息，并封装为Vo
     * @param activityList 查询出的活动
     * @param schoolList 所有的校区
     * @return 活动教师信息VoList
     */
    private List<ActivityRealTeacherVo> getActivityRealTeacherVoList(List<Activity> activityList, List<School> schoolList) {
        // 将查询的活动的id取出拼接为list
        List<String> activityidList = activityList.stream().map(Activity::getId).collect(Collectors.toList());
        // 查询活动教师信息
        List<ActivityRealTeacher> activityRealTeacherList = activityRealTeacherMapper.queryByIdBatch(activityidList);

        // 将查询的活动教师的id取出拼接为list
        List<String> teacheridList = activityRealTeacherList.stream().map(ActivityRealTeacher::getTeacherid).collect(Collectors.toList());
        // 查询教师信息
        List<Teacher> teacherList = teacherService.queryByIdBatch(teacheridList).getData();

        // 封装
        List<ActivityRealTeacherVo> activityRealTeacherVoList = new ArrayList<>();
        // 循环查出的所有的活动教师，按照  一个活动对应多个校区，每个校区对应多个教师  分组
        for (ActivityRealTeacher art : activityRealTeacherList) {
            ActivityRealTeacherVo activityRealTeacherVo = new ActivityRealTeacherVo();
            // 第一个ActivityRealTeacherVo直接添加
            if (activityRealTeacherVoList.size() == 0) {
                activityRealTeacherVo.setActivityid(art.getActivityid());
                activityRealTeacherVo.setSchoolid(art.getSchoolid());
                ActivityRealTeacherDetailVo activityRealTeacherDetailVo = getActivityRealTeacherDetailVo(art, teacherList);
                // 新建activityRealTeacherDetailVoList存放教师信息
                List<ActivityRealTeacherDetailVo> activityRealTeacherDetailVoList = new ArrayList<>();
                activityRealTeacherDetailVoList.add(activityRealTeacherDetailVo);
                activityRealTeacherVo.setActivityRealTeacherDetailVo(activityRealTeacherDetailVoList);
            }

            // 除第一个外，需要判断同一个活动的同一个校区
            for (ActivityRealTeacherVo artv : activityRealTeacherVoList) {
                // 封装数据
                activityRealTeacherVo.setActivityid(art.getActivityid());
                activityRealTeacherVo.setSchoolid(art.getSchoolid());
                ActivityRealTeacherDetailVo activityRealTeacherDetailVo = getActivityRealTeacherDetailVo(art, teacherList);
                List<ActivityRealTeacherDetailVo> activityRealTeacherDetailVoList;

                if (artv.getActivityid().equals(art.getActivityid()) && artv.getSchoolid().equals(art.getSchoolid())) {
                    // 如果教师是同一个活动同一个校区的，获取已经存放过的教师list，加入新的教师，再放回
                    activityRealTeacherDetailVoList = artv.getActivityRealTeacherDetailVo();
                }else {
                    // 如果还没有存放过某个活动某校区的教师，则需要新建activityRealTeacherDetailVoList来存放
                    activityRealTeacherDetailVoList = new ArrayList<>();
                }
                activityRealTeacherDetailVoList.add(activityRealTeacherDetailVo);
                activityRealTeacherVo.setActivityRealTeacherDetailVo(activityRealTeacherDetailVoList);
            }

            activityRealTeacherVoList.add(activityRealTeacherVo);
        }

        return activityRealTeacherVoList;
    }

    /**
     * 根据 活动教师信息 和 教师信息封装 活动教师DetailVo对象
     * @param activityRealTeacher  活动教师信息
     * @param teacherList  教师信息
     * @return  活动教师DetailVo对象
     */
    private ActivityRealTeacherDetailVo getActivityRealTeacherDetailVo(ActivityRealTeacher activityRealTeacher, List<Teacher> teacherList) {
        // 封装数据
        ActivityRealTeacherDetailVo activityRealTeacherDetailVo = new ActivityRealTeacherDetailVo();
        activityRealTeacherDetailVo.setTeacherid(activityRealTeacher.getTeacherid());
        // 教师名称
        for (Teacher tea : teacherList) {
            if (activityRealTeacher.getTeacherid().equals(tea.getId())) {
                activityRealTeacherDetailVo.setNickname(tea.getName());
                break;
            }
        }
        activityRealTeacherDetailVo.setStartdate(activityRealTeacher.getStartdate());
        activityRealTeacherDetailVo.setEnddate(activityRealTeacher.getEnddate());
        activityRealTeacherDetailVo.setAttend(activityRealTeacher.getAttend());

        return activityRealTeacherDetailVo;
    }

    /**
     * 新增数据
     *
     * @param activityForm 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public ResponseVo<String> insert(ActivityForm activityForm) {
        // 1、非空校验放在Controller中

        // 2、生成活动表数据，并插入
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityForm,activity);

        String id = "Act" + UUID.randomUUID().toString().replace("-", "");
        activity.setId(id);

        int insertCount = activityMapper.insert(activity);
        if(insertCount != 1){
            return ResponseVo.error("添加失败");
        }

        // 3、生成活动地点表数据，并插入
        List<Map<String, String>> activityRealAddressMapList = activityForm.getActivityRealAddress();
        List<ActivityRealAddress> activityRealAddressList = new ArrayList<>();
        for (Map<String, String> map : activityRealAddressMapList) {
            ActivityRealAddress ara = new ActivityRealAddress();
            ara.setActivityid(activity.getId());
            ara.setSchoolid(map.get("schoolid"));
            ara.setActivityaddress(map.get("activityAddress"));
            ara.setUpdatedPerson(activityForm.getUpdatedPerson());
            activityRealAddressList.add(ara);
        }

        int addressInsertCount = activityRealAddressMapper.insertBatch(activityRealAddressList);
        if (addressInsertCount != activityRealAddressList.size()) {
            return ResponseVo.error("添加失败");
        }

        // 4、生成活动教师表数据，并插入
        List<Map<String, Object>> activityRealTeacherMapList = activityForm.getActivityRealTeacher();
        List<ActivityRealTeacher> activityRealTeacherList = new ArrayList<>();
        for (Map<String, Object> activityRealTeacherMap : activityRealTeacherMapList) {
            List<Map<String,String>> teacherList = (List<Map<String,String>>) activityRealTeacherMap.get("teacherList");
            for (Map<String, String> teacher : teacherList) {
                ActivityRealTeacher art = new ActivityRealTeacher();
                art.setActivityid(activity.getId());
                art.setTeacherid(teacher.get("teacherid"));
                art.setSchoolid(activityRealTeacherMap.get("schoolid").toString());
                art.setStartdate(teacher.get("startDate"));
                art.setEnddate(teacher.get("endDate"));
                art.setAttend(Integer.valueOf(teacher.get("attend")));
//                art.setAttend(0);// 添加活动时，默认老师还没参加
                art.setUpdatedPerson(activityForm.getUpdatedPerson());
                activityRealTeacherList.add(art);
            }

        }

        int teacherInsertCount = activityRealTeacherMapper.insertBatch(activityRealTeacherList);
        if (teacherInsertCount != activityRealTeacherList.size()) {
            return ResponseVo.error("添加失败");
        }

        return ResponseVo.success("添加成功");
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity update(Activity activity) {
        this.activityMapper.update(activity);
        return this.queryById(activity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public ResponseVo<String> deleteById(String id) {
        // 1、删除  活动地址表  数据
        ResponseVo<String> araRV = activityRealAddressService.deleteByActivityId(id);
        if (araRV.getStatus().equals(0)) {
            return araRV;
        }
        // 2、删除  活动教师表  数据

        // 3、删除  活动表  数据

        return null;
    }
}
