package com.zh.studentmanage.controller;

import com.zh.studentmanage.pojo.Activity;
import com.zh.studentmanage.service.ActivityService;
import com.zh.studentmanage.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @GetMapping("/query")
    public ResponseVo query(Activity activity, int currentPage, int pageSize) {
        return activityService.queryByParamLimit(activity, currentPage, pageSize);
    }

}
