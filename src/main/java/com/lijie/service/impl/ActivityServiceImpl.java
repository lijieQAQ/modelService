package com.lijie.service.impl;

import com.lijie.Dao.ActivityDao;
import com.lijie.pojo.Activity;
import com.lijie.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * Created by lijie on 2018/5/28.
 */
@Service
public class ActivityServiceImpl implements ActivityService {


    @Autowired
    private ActivityDao activityDao;

    @Override
    public int saveOrUpdate(Activity activity) {
        Activity ac;
        ac = activityDao.saveAndFlush(activity);
        if (ac == null) {
            return  0;
        }else {
            return  1;
        }
    }

    @Override
    public Page<Activity> findAllActivityByPage(int pageSize, int pageNumber) {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable page = new PageRequest(pageNumber,pageSize,sort);
        return activityDao.findAll(page);
    }

    @Override
    public Page<Activity> findActivitiesByType(int staffId, String activitytype, int pageSize, int pageNumber) {
        Pageable page = new PageRequest(pageNumber,pageSize);
        return activityDao.findActivitiesByType(staffId,activitytype,page);
    }

//    @Override
//    public List<Activity> findActivitiesByType(int staffId, String activitytype) {
//        return activityDao.findActivitiesByType(staffId,activitytype);
//    }

    @Override
    public Activity findActivitiesById(@RequestBody Integer id) {
        return  activityDao.getOne(id);
    }

}
