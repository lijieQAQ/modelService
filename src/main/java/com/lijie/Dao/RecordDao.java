package com.lijie.Dao;

import com.lijie.pojo.Record;
import com.lijie.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {
    Record findByActivitytypeAndActivityIdAndStaffId(String Activitytype,Integer ActivityId ,String StaffId);
}