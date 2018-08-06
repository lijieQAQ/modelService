package com.lijie.Dao;

import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityDao extends JpaRepository<Activity,Integer> {
    Page<Activity> findAllByisCarousel(char c, Pageable page);
}
