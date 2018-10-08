package com.lijie.service;


import com.lijie.pojo.Activity;
import com.lijie.pojo.Record;

public interface RecordService {
    int saveOrUpdate(Record record);

    Record findActivityStatus(Record record);

    void deleteActivity(Record record);

    void updateApplyActivity(Integer activityId, Integer staffId, String activitytype, String status);

    void deleteActivityDCJ(Integer activityId, Integer staffId, String activitytype, String status);

    void clear();

    int  deleteRecorgByAct(Integer activityId);

    int findApplyNum(Integer activityId);
}
