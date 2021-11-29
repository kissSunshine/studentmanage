package com.zh.studentmanage.service.impl;

import com.zh.studentmanage.dao.ActivityRealAddressMapper;
import com.zh.studentmanage.pojo.ActivityRealAddress;
import com.zh.studentmanage.service.ActivityRealAddressService;
import com.zh.studentmanage.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("activityRealAddressServiceI")
public class ActivityRealAddressServiceImpl implements ActivityRealAddressService {

    @Resource
    private ActivityRealAddressMapper activityRealAddressMapper;

    @Override
    public ResponseVo<List<ActivityRealAddress>> queryByIdBatch(List<String> activityidList) {
        List<ActivityRealAddress> activityRealAddressList = activityRealAddressMapper.queryByIdBatch(activityidList);

        if (activityRealAddressList.size() == 0) {
            return ResponseVo.error("未查询到活动地址信息");
        }

        return ResponseVo.success("查询活动地址信息成功", activityRealAddressList);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Override
    public ResponseVo<String> deleteByActivityId(String id) {
        // 1、删除  活动地址表  数据
        int deleteCount = activityRealAddressMapper.deleteById(id);
        // 添加活动时，是一定会添加活动地点的，所以删除影响行数不可能小于等于0
        if (deleteCount <= 0) {
            return ResponseVo.error("删除失败");
        }

        return ResponseVo.success("删除成功");
    }
}
