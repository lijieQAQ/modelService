package com.lijie.controller;

import com.lijie.Util.ResUtil;
import com.lijie.pojo.Activity;
import com.lijie.pojo.ResultPojo;
import com.lijie.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/leader")
public class LeaderController {
    @Autowired
    private LeaderService leaderService;

    @RequestMapping("/getActivityList")
    public void getActivityList(HttpServletResponse response,char isCarousel, int pageSize, int pageNumber) {
        ResultPojo result = new ResultPojo();
        Page<Activity> activityList= leaderService.getActivityList(isCarousel, pageSize, pageNumber);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(activityList);
        ResUtil.ResString(response, result);
    }
}
