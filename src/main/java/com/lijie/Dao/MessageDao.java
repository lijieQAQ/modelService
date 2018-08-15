package com.lijie.Dao;

import com.lijie.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {

    Message findByMessageId(Integer messageId);

    List<Message> findByUser2(Integer userId);


//    List<Message> findByMessageList(Integer userId);


    List<Message> findByUser2AndStatusOrderBySendTime(Integer userId, String status);


    List<Message> findByUser1AndUser2OrderBySendTime(Integer userId1, Integer userId2);

    @Query(value = "select " +
            "    msg.user1 AS user1, msg.user2 AS user2, count(msg.messageId) AS unopenCnt " +
            "    from Message msg, Staff usr1, Staff usr2 " +
//            "    where msg.user1 = usr1.id and msg.user2 = usr2.id " +
            "    where ( msg.user1 = ?1 or msg.user2 = ?1 ) " +
            "    and msg.status = '0' " +
            "    group by msg.user1, msg.user2 " +
            "    order by msg.user1, msg.user2")
    List<Message> selectUnopenCntByUser(Integer userId);
}