package com.lijie.controller;

import com.lijie.pojo.Activity;
import com.lijie.pojo.Staff;
import com.lijie.service.ActivityService;
import com.lijie.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private StaffService staffService;
    @RequestMapping("/getActivityList")
    public List<Activity> getActivityList(String type ) {
        List<Activity> list = activityService.findActivitiesByType(1, type);
        return list;
    }

    @RequestMapping("/getPersonInformation")
    public Staff getPersonInformation() {
        Staff staff = staffService.findById(1);
        return staff;
    }
    @RequestMapping("/updatePerson")
    public int updatePerson(Staff staff) {
        int num = staffService.saveOrUpdate(staff);
        return num;
    }


}
