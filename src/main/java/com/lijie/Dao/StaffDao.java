package com.lijie.Dao;

import com.lijie.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffDao extends JpaRepository<Staff,Integer> {
    List<Staff> findByname(String name);
    Staff findById(String id);
    Staff findByMobileAndPassword(String mobile, String password);
    Staff findByMobile(String mobile);
}
