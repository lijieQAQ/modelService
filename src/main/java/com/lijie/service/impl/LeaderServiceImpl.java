package com.lijie.service.impl;

import com.lijie.Dao.ActivityDao;
import com.lijie.pojo.Activity;
import com.lijie.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


/**
 * Created by lijie on 2018/5/28.
 */
@Service
public class LeaderServiceImpl implements LeaderService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public Page<Activity> getActivityList(char isCarousel, int pageSize, int pageNumber) {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable page = new PageRequest(pageNumber,pageSize,sort);
        return activityDao.findAllByisCarousel(isCarousel, page);
    }
}
