package com.lijie.controller;

import com.lijie.Util.MD5Util;
import com.lijie.Util.ResUtil;
import com.lijie.pojo.Activity;
import com.lijie.pojo.ResultPojo;
import com.lijie.pojo.Staff;
import com.lijie.service.ActivityService;
import com.lijie.service.StaffService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/backManagement")
public class BackController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private ActivityService activityService;
    @Value("${web.upload-img-path}")
    private String imagesPath;
    @Value("${web.upload-video-path}")
    private String videoPath;
    private static final Log log = LogFactory.getLog(BackController.class);
    @RequestMapping("/login")
    public void login(HttpServletResponse response, String name, String password) {
        log.info("登录名称" + name);
        log.info("登录密码" + password);
        Staff staff = staffService.login(name);
        ResultPojo result = new ResultPojo();
        if(staff == null) {
            result.setStatus("fail");
            result.setInfo("用户名错误或用户不存在");
        }else {
            if(MD5Util.MD5(password).equalsIgnoreCase(staff.getPassword().trim())) {
                result.setStatus("success");
                result.setInfo("登录成功");
                result.setData(staff);
            }else {
                result.setStatus("fail");
                result.setInfo("密码错误");
            }
        }
        ResUtil.ResString(response, result);
    }
    @RequestMapping("/uploadPhoto")
    public void uploadPhoto(@RequestParam("file")MultipartFile file, HttpServletResponse response) {
        ResultPojo result = new ResultPojo();
        if (Objects.isNull(file) || file.isEmpty()) {
            log.error("文件为空");
            result.setInfo("文件为空，请重新上传");
            result.setStatus("fail");
        }

        try {
            String imagesName = System.currentTimeMillis() + ".png";
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imagesPath + "/" + imagesName);
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(imagesPath + "/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件写入成功...");
            result.setInfo("文件上传成功");
            result.setData(imagesName);
            result.setStatus("success");
        } catch (IOException e) {
            e.printStackTrace();
            result.setInfo("后端异常...");
            result.setStatus("fail");
        }
        ResUtil.ResString(response, result);
    }
    @RequestMapping("/uploadVideo")
    public void uploadVideo(@RequestParam("file")MultipartFile file, HttpServletResponse response) {
        ResultPojo result = new ResultPojo();
        if (Objects.isNull(file) || file.isEmpty()) {
            log.error("文件为空");
            result.setInfo("文件为空，请重新上传");
            result.setStatus("fail");
        }

        try {
            String videoName = System.currentTimeMillis() + ".mp4";
            byte[] bytes = file.getBytes();
            Path path = Paths.get(videoPath + "/" + videoName);
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(videoPath + "/"));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            log.debug("文件写入成功...");
            result.setInfo("文件上传成功");
            result.setData(videoName);
            result.setStatus("success");
        } catch (IOException e) {
            e.printStackTrace();
            result.setInfo("后端异常...");
            result.setStatus("fail");
        }
        ResUtil.ResString(response, result);
    }
    @RequestMapping("/addActivity")
    public void addActivity(HttpServletResponse response, Activity activity) {
        activity.setCreateDate(new Date());
        log.info("参数" + activity.toString());
        ResultPojo result = new ResultPojo();
        int result1 = activityService.saveOrUpdate(activity);
        if(result1 == 1) {
            result.setInfo("添加成功");
            result.setStatus("success");
        } else  {
            result.setInfo("添加失败");
            result.setStatus("fail");
        }
        ResUtil.ResString(response, result);
    }
    @RequestMapping("/getActivityList")
    public void getActivityList(HttpServletResponse response, int pageSize, int pageNumber) {
        ResultPojo result = new ResultPojo();
        Page<Activity> activityList= activityService.findAllActivityByPage(pageSize, pageNumber);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(activityList);
        ResUtil.ResString(response, result);
    }
    @RequestMapping("/getStaffList")
    public void getStaffList(HttpServletResponse response, int pageSize, int pageNumber) {
        ResultPojo result = new ResultPojo();
        Page<Staff> staffList= staffService.findAllStaffByPage(pageSize, pageNumber);
        result.setInfo("查询成功");
        result.setStatus("success");
        result.setData(staffList);
        ResUtil.ResString(response, result);
    }
}
