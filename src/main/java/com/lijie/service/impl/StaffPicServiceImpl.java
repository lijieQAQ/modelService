package com.lijie.service.impl;

import com.lijie.Dao.StaffPicDao;
import com.lijie.pojo.StaffPic;
import com.lijie.service.StaffPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<StaffPic> findPicByStaffId(Integer staffId) {
        return staffPicDao.findPicByStaffId(staffId);
    }
}
