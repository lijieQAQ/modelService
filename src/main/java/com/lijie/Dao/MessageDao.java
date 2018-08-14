package com.lijie.Dao;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageDao extends JpaRepository<Message ,Integer> {

    Message findByMessageId(Integer messageId);

    List<Message> findByUser2(Integer userId);

    List<Message> findByUser2OrderBySendTime(Integer userId);


    List<Message> findByUser2AndStatusOrderBySendTime(Integer userId, String status);


    List<Message> findByUser1AndUser2OrderBySendTime(Integer userId1,Integer userId2);


}