package com.lijie.service;


import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;


/**
 * Created by lijie on 2018/5/28.
 */
public interface ActivityService {

    int saveOrUpdate(Activity activity);

    Page<Activity> findAllActivityByPage(int pageSize, int pageNumber);
}
