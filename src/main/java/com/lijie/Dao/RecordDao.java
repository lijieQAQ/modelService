package com.lijie.Dao;

import com.lijie.pojo.Record;
import com.lijie.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface RecordDao extends JpaRepository<Record,Integer> {

    @Query(value = "select * from record where activity_id=?2 and activitytype=?1 and staff_id=?3 and status != 0", nativeQuery = true)
    Record findByActivitytypeAndActivityIdAndStaffIdAndStatus(String Activitytype,Integer ActivityId ,Integer staffId);
    @Modifying
    @Transactional
    @Query(value = "update  record set status = ?4 where activity_id=?1 and activitytype=?2 and staff_id=?3", nativeQuery = true)
    void deleteByActivityIdAndActivitytype(Integer activityId, String activitytype,Integer staffId,String status);
    @Transactional
    @Modifying
    @Query(value = " update record set status=?4 where activity_id=?1 and activitytype=?3 and staff_id=?2", nativeQuery = true)
    void updateApplyActivity(Integer activityId, Integer staffId, String activitytype, String status);

    @Transactional
    @Modifying
    @Query(value = " update record set status= '0' where activity_id=?1 ", nativeQuery = true)
    int updateRecorgByAct(Integer activityId);

    @Query(value = " SELECT count(0) from record  where activity_id=?1 and status!=0 and activitytype ='a'", nativeQuery = true)
    int findApplyNum(Integer activityId);
}
