package com.lijie.service.impl;

import com.lijie.Dao.RecordDao;
import com.lijie.pojo.Record;
import com.lijie.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceimpl implements RecordService {
    @Autowired
    RecordDao RecordDao;
    @Override
    public int saveOrUpdate(Record record) {
        Record rc;
        rc = RecordDao.saveAndFlush(record);
        if (rc == null) {
            return  0;
        }else {
            return  1;
        }
    }

    @Override
    public Record findActivityStatus(Record record) {
      return   RecordDao.findByActivitytypeAndActivityIdAndStaffId(record.getActivitytype(),record.getActivityId(),record.getStaffId());
    }
}
