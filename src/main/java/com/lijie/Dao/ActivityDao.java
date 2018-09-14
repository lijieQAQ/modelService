package com.lijie.Dao;

import com.lijie.pojo.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityDao extends JpaRepository<Activity,Integer> {
    Page<Activity> findAllByisCarousel(char c, Pageable page);
    @Query(value = "select * from activity  where id in ( select r.activity_id from record r where r.staff_id = ?1 and r.activitytype = ?2)",nativeQuery = true)
    List<Activity> findActivitiesByType( int staffId, String activitytype);

}
