package com.lijie.service;


import com.lijie.pojo.StaffPic;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
public interface StaffPicService {

    StaffPic findByStaffId(Integer staffId);

    StaffPic saveAndUpdate(StaffPic staffPic);


    List<StaffPic> findPicByStaffId(Integer staffId);
}
