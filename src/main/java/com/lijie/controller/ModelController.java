package com.lijie.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lijie.Util.ResUtil;
import com.lijie.pojo.Activity;
import com.lijie.pojo.Record;
import com.lijie.pojo.ResultPojo;
import com.lijie.pojo.Staff;
import com.lijie.service.ActivityService;
import com.lijie.service.RecordService;
import com.lijie.service.StaffService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private RecordService recordService;
    @RequestMapping("/getActivityList")
    public void getActivityList(HttpServletResponse response,String type,Integer staffId,int pageSize, int pageNumber ) {
        ResultPojo result = new ResultPojo();
        Page<Activity> activityList= activityService.findActivitiesByType(staffId,type,pageSize,pageNumber);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(activityList);
        ResUtil.ResString(response, result);
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
    @PostMapping("/getActivityById")
    public Activity getActivityById(Integer id){
       return activityService.findActivitiesById(id);
    }

    @PostMapping("/collectedActivity")
    public void collectedActivity(HttpServletResponse response,Record record){
        ResultPojo result = new ResultPojo();
        int result1 = recordService.saveOrUpdate(record);
        if(result1 == 1) {
            result.setInfo("添加成功");
            result.setStatus("success");
        } else  {
            result.setInfo("添加失败");
            result.setStatus("fail");
        }
        ResUtil.ResString(response, result);
    }



    @PostMapping("/findActivityStatus")
    public void findActivityStatus(HttpServletResponse response,Record record){
        ResultPojo result = new ResultPojo();
        record.setActivitytype("s");
        Record records = recordService.findActivityStatus(record);
        JSONObject jsonObject = new JSONObject();
        if(records!=null){
            jsonObject.put("collected",true);
        }else{
            jsonObject.put("collected",false);
        }
        record.setActivitytype("a");
        records = recordService.findActivityStatus(record);
        if(records!=null){
            jsonObject.put("apply",true);
        }else{
            jsonObject.put("apply",false);
        }
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(jsonObject);
        ResUtil.ResString(response, result);
    }

    @PostMapping("/deleteApplyActivity")
    public void deleteApplyActivity(HttpServletResponse response, Record record){
        ResultPojo result = new ResultPojo();
        recordService.deleteActivity(record);
        record.setActivitytype("dc");
        recordService.deleteActivity(record);
        result.setStatus("success");
        ResUtil.ResString(response, result);
    }




}
