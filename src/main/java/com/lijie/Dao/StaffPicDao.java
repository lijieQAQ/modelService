package com.lijie.Dao;

import com.lijie.pojo.StaffPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffPicDao extends JpaRepository<StaffPic,Integer> {
    StaffPic findByStaffId(Integer staffId);
}
