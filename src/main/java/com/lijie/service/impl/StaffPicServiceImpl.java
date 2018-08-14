package com.lijie.service.impl;

import com.lijie.Dao.StaffPicDao;
import com.lijie.pojo.StaffPic;
import com.lijie.service.StaffPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lijie on 2018/5/28.
 */
@Service
public class StaffPicServiceImpl implements StaffPicService {

    @Autowired
    private StaffPicDao staffPicDao;

    @Override
    public StaffPic findByStaffId(Integer staffId) {
        return staffPicDao.findByStaffId(staffId);
    }

    @Override
    public StaffPic saveAndUpdate(StaffPic staffPic) {
        return staffPicDao.saveAndFlush(staffPic);
    }
}
