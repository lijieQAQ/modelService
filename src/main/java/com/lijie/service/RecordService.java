package com.lijie.service;


import com.lijie.pojo.Record;

public interface RecordService {
    int saveOrUpdate(Record record);

    Record findActivityStatus(Record record);
}
