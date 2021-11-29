package com.zh.studentmanage.service;

import com.zh.studentmanage.pojo.ActivityRealAddress;
import com.zh.studentmanage.vo.ResponseVo;

import java.util.List;

public interface ActivityRealAddressService {

    /**
     * 通过活动idList批量查询
     * @param activityidList 活动id列表
     * @return 活动地址信息
     */
    ResponseVo<List<ActivityRealAddress>> queryByIdBatch(List<String> activityidList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ResponseVo<String> deleteByActivityId(String id);

}
