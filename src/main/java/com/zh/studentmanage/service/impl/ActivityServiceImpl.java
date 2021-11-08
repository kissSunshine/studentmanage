package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ActivityMapper;
import com.zh.studentmanage.dao.ActivityRealAddressMapper;
import com.zh.studentmanage.pojo.Activity;
import com.zh.studentmanage.pojo.ActivityRealAddress;
import com.zh.studentmanage.service.ActivityService;
import com.zh.studentmanage.vo.ActivityVo;
import com.zh.studentmanage.vo.PageVo;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivityRealAddressMapper activityRealAddressMapper;

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
        // 4、查询活动地点信息
        // 将查询的所有活动的id取出拼接为list
        List<String> activityidList = activityList.stream().map(Activity::getId).collect(Collectors.toList());
        List<ActivityRealAddress> activityRealAddressList = activityRealAddressMapper.queryByIdBatch(activityidList);

        // 5、封装VO对象
        List<ActivityVo> activityVoList = new ArrayList<>();
        for (Activity act : activityList) {
            // 先将已有数据封装到VO
            ActivityVo activityVo = new ActivityVo();
            BeanUtils.copyProperties(act, activityVo);

            activityVoList.add(activityVo);
        }
        // 6、封装分页数据
        PageVo<List<ActivityVo>> activityListPageVo = new PageVo<>(activityVoList, activityCount);

        return ResponseVo.success("查询成功", activityListPageVo);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseVo<String> insert(Activity activity) {
        // 1、非空校验放在Controller中

        // 2、填充其余信息
        // 生成UUID作为主键
        String id = "Act" + UUID.randomUUID().toString().replace("-", "");
        activity.setId(id);

        // 3、插入数据库
        int insertCount = activityMapper.insert(activity);
        if(insertCount != 1){
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
    public boolean deleteById(String id) {
        return this.activityMapper.deleteById(id) > 0;
    }
}
