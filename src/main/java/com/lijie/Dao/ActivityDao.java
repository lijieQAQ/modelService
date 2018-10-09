package com.lijie.Dao;

import com.lijie.pojo.Activity;
import javafx.scene.chart.ValueAxis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by lijie on 2018/5/28.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityDao extends JpaRepository<Activity,Integer> {
    Page<Activity> findAllByisCarousel(char c, Pageable page);
    @Query(value = "select * from activity  where id in ( select r.activity_id from record r where r.staff_id = ?1 and r.activitytype = ?2 and r.status!='0' and r.status!='4' ORDER by createdate DESC)",countQuery = "select count(*) from activity  where id in ( select r.activity_id from record r where r.staff_id = ?1 and r.activitytype = ?2 and r.status !='0' ORDER by createdate DESC )", nativeQuery = true)
    Page<Activity> findActivitiesByType( int staffId, String activitytype,Pageable page);

    @Query(value = "select a.*,s.name staffname from activity a,staff s where a.leader_id = s.id and a.id=?1" , nativeQuery = true)
    Map getEvaActivityById(Integer id);

    @Modifying
    @Transactional
    @Query(value="update activity set look_num = look_num+1 where id=?1" , nativeQuery = true)
    int updateActLook(Integer activityId);
}
