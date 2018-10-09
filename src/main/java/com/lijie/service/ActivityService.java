package com.lijie.service;


import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


/**
 * Created by lijie on 2018/5/28.
 */
public interface ActivityService {

    int saveOrUpdate(Activity activity);

    Page<Activity> findAllActivityByPage(int pageSize, int pageNumber);
    Page<Activity> findActivitiesByType (int staffId, String activitytype, int pageSize, int pageNumber);

    Activity findActivitiesById(Integer id);

    Map getEvaActivityById(Integer id);

    void deleteActivity(Activity activity);

    int updateActLook(Integer activityId);
}
