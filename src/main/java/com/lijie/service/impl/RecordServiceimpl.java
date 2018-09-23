package com.lijie.service.impl;

import com.lijie.Dao.RecordDao;
import com.lijie.pojo.Activity;
import com.lijie.pojo.Record;
import com.lijie.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceimpl implements RecordService {
    @Autowired
    RecordDao recordDao;
    @Override
    public int saveOrUpdate(Record record) {
        Record rc;
        rc = recordDao.saveAndFlush(record);
        if (rc == null) {
            return  0;
        }else {
            return  1;
        }
    }

    @Override
    public Record findActivityStatus(Record record) {

      return   recordDao.findByActivitytypeAndActivityIdAndStaffIdAndStatus(record.getActivitytype(),record.getActivityId(),record.getStaffId());
    }

    @Override
    public void deleteActivity(Record record) {
        recordDao.deleteByActivityIdAndActivitytype(record.getActivityId(),record.getActivitytype(),record.getStaffId(),record.getStatus());
    }

    @Override
    public void updateApplyActivity(Integer activityId, Integer staffId, String activitytype, String status) {
        recordDao.updateApplyActivity(activityId,staffId,activitytype,status);
    }

    @Override
    public void deleteActivityDCJ(Integer activityId, Integer staffId, String activitytype, String status) {
        recordDao.updateApplyActivity(activityId,staffId,activitytype,status);
    }

    @Override
    public void clear() {

    }
}
