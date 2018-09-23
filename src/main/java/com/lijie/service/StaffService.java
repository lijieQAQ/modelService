package com.lijie.service;

import com.lijie.pojo.Staff;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
public interface StaffService {

    Staff login(String name);

    Page<Staff> findAllStaffByPage(int pageSize, int pageNumber);
    Staff findById(int id);
    int saveOrUpdate(Staff staff);
    Staff findByMobileAndPassword(String mobile, String password);
    int  findByMobile(String mobile);

    Page<Staff> findStaffByIdAndActivitytype(Integer id,int pageSize, int pageNumber,String status,String activitytype);
}
