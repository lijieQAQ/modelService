package com.lijie.service;


import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by lijie on 2018/5/28.
 */
public interface ActivityService {

    int saveOrUpdate(Activity activity);

    Page<Activity> findAllActivityByPage(int pageSize, int pageNumber);
    List<Activity> findActivitiesByType (int staffId, String activitytype);

    Activity findActivitiesById(Integer id);
}
