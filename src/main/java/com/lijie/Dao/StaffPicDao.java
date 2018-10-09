package com.lijie.Dao;

import com.lijie.pojo.StaffPic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface StaffPicDao extends JpaRepository<StaffPic,Integer> {
    StaffPic findByStaffId(Integer staffId);

    @Query(value = "select * from staff_pic s where s.body != '' and s.make_up != '' and s.model_card !='' and s.no_make_up !='' and s.staff_id =?1 ", nativeQuery = true)
    List<StaffPic> findPicByStaffId(Integer staffId);
}
