package com.lijie.service;


import com.lijie.pojo.StaffPic;

/**
 * Created by lijie on 2018/5/28.
 */
public interface StaffPicService {

    StaffPic findByStaffId(Integer staffId);

    StaffPic saveAndUpdate(StaffPic staffPic);
}
