package com.lijie.controller;

import com.lijie.pojo.Staff;
import com.lijie.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private StaffService staffService;
    @RequestMapping("/userLogin")
    public Staff userLogin(Staff staff){
        String mobile = staff.getMobile();
        String password = staff.getPassword();
        Staff user = staffService.findByMobileAndPassword(mobile,password );
        return user;
    }
    @PostMapping("/register")
    public int register(Staff staff){
        int num= staffService.saveOrUpdate(staff);
        return num;
    }
    @PostMapping("/checkphone")
    public int checkphone(Staff staff){
        return staffService.findByMobile(staff.getMobile());
    }

}
