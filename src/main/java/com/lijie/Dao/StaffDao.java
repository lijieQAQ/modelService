package com.lijie.Dao;

import com.lijie.pojo.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select * from staff where id in (select staff_id from record where activitytype=?3 and activity_id = ?1 and status = ?2  order by createdate) ",countQuery ="select count(*) from staff where id in (select staff_id from record where activitytype=?3 and activity_id = ?1 and status = ?2 order by createdate) ", nativeQuery = true)
    Page<Staff> findStaffByIdAndActivitytype(Integer id,Pageable page,String status,String activitytype);
}
