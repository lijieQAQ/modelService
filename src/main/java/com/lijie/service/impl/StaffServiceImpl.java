package com.lijie.service.impl;

import com.lijie.Dao.StaffDao;
import com.lijie.pojo.Staff;
import com.lijie.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao StaffDao;
    @Override
    public Staff login(String name) {
        List<Staff> staffList = StaffDao.findByname(name);
        if(staffList.size()>0) {
            return staffList.get(0);
        }else {
            return null;
        }

    }

    @Override
    public Page<Staff> findAllStaffByPage(int pageSize, int pageNumber) {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable page = new PageRequest(pageNumber,pageSize,sort);
        return StaffDao.findAll(page);
    }
}
