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

    List<MessageItem> findByUser2(Integer userId);

    List<MessageItem> findByUser2OrderBySendTime(Integer userId);


    List<MessageItem> findByUser2AndStatusOrderBySendTime(Integer userId, String status);


    List<MessageItem> findByUser1AndUser2OrderBySendTime(Integer userId1,Integer userId2);

    int insert(Message record);
    int insertSelective(Message record);

    int updateByPrimaryKeySelective(Message record);
    int updateByPrimaryKey(Message record);
    int updateStatusByUsers(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

    int deleteByMessageId(Integer messageId);

}