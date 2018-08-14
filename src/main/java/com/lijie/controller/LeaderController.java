package com.lijie.controller;

import com.lijie.Util.ResUtil;
import com.lijie.pojo.Activity;
import com.lijie.pojo.ResultPojo;
import com.lijie.pojo.StaffPic;
import com.lijie.service.LeaderService;
import com.lijie.service.StaffPicService;
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
    @Autowired
    private StaffPicService staffPicService;

    @RequestMapping("/getActivityList")
    public void getActivityList(HttpServletResponse response,char isCarousel, int pageSize, int pageNumber) {
        ResultPojo result = new ResultPojo();
        Page<Activity> activityList= leaderService.getActivityList(isCarousel, pageSize, pageNumber);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(activityList);
        ResUtil.ResString(response, result);
    }

    @RequestMapping("/findByStaffId")
    public void findByStaffId(HttpServletResponse response, Integer staffId) {
        ResultPojo result = new ResultPojo();
        StaffPic staffPic = staffPicService.findByStaffId(staffId);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(staffPic);
        ResUtil.ResString(response, result);
    }

    @RequestMapping("/saveAndUpdate")
    public void saveAndUpdate(HttpServletResponse response, StaffPic staffPic) {
        ResultPojo result = new ResultPojo();
        StaffPic staffP = staffPicService.saveAndUpdate(staffPic);
        result.setInfo("操作成功");
        result.setStatus("success");
        result.setData(staffP);
        ResUtil.ResString(response, result);
    }

}
