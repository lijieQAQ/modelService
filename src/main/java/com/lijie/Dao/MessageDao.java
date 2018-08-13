package com.lijie.Dao;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageDao extends JpaRepository<Message ,Integer> {

    Message selectByPrimaryKey(Integer messageId);

    List<MessageItem> selectSysMsgByUser(Integer userId);
    List<MessageItem> selectLatestMsgByUser(Integer userId);
    List<MessageItem> selectUnopenCntByUser(Integer userId);

    List<MessageItem> selectMsgByUsers(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

    int insert(Message record);
    int insertSelective(Message record);

    int updateByPrimaryKeySelective(Message record);
    int updateByPrimaryKey(Message record);
    int updateStatusByUsers(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

    int deleteByPrimaryKey(Integer messageId);

}