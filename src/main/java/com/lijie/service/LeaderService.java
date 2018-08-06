package com.lijie.service;


import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;

public interface LeaderService {


    Page<Activity> getActivityList(char isCarousel, int pageSize, int pageNumber);
}

