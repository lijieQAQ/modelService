package com.lijie.service;

import com.lijie.pojo.Staff;
import org.springframework.data.domain.Page;

/**
 * Created by lijie on 2018/5/28.
 */
public interface StaffService {

    Staff login(String name);

    Page<Staff> findAllStaffByPage(int pageSize, int pageNumber);
}
