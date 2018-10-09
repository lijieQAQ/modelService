package com.lijie.service.impl;

import com.lijie.Dao.MessageDao;
import com.lijie.Util.ConstantField;
import com.lijie.pojo.Message;
import com.lijie.service.MessageService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao mapper;
    @PersistenceContext
    private EntityManager em;

    /**
     * 发消息，创建消息数据
     */
    public int send(Message msg) {

        // 设置消息类型，02-用户消息
        msg.setMsgType(ConstantField.MSG_TYPE_USR);
        // 设置消息状态，0-未读
        msg.setStatus(ConstantField.MSG_STATUS_UNOPEN);
        // 送信时间
        msg.setSendTime(new Date());
        // 创建信息
        msg.setCreateId(msg.getSpeaker());
        msg.setCreateDate(new Date());
        Message message = mapper.saveAndFlush(msg);
        if (message != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 根据用户ID检索消息
     */
    public List<List<HashMap>> selectAllByUser(Integer userId) {
        Query query = em.createNativeQuery("select " +
                "    MSG.message_id messageId, MSG.msg_type msgType, MSG.user1 user1, MSG.user2 user2, MSG.speaker speaker, MSG.message message, MSG.send_time sendTime, MSG.open_time openTime, MSG.status status, " +
                "    MSG.del_flg delFlg, MSG.create_id createId, MSG.create_date createDate, MSG.update_id updateId, MSG.update_date updateDate, " +
                "    USR1.name AS userName1, USR1.avatar AS portrait1, USR2.name AS userName2, USR2.avatar AS portrait2, " +
                "    0 AS unopenCnt " +
                "    from message MSG, staff USR1, staff USR2, " +
                "    ( select user1,  user2, MAX(send_time) AS SEND_TIME " +
                "      from message  " +
                "      where ( user1 = ? or user2 = ? ) " +
                "      group by user1 asc, user2 asc ) AS A " +
                "    where MSG.user1 = A.user1 and MSG.user2 = A.user2 and MSG.send_time = A.send_time AND MSG.user1 = USR1.id" +
                "  AND MSG.user2 = USR2.id" +
                "    order by MSG.user1, MSG.user2");
        query.setParameter(1, userId);
        query.setParameter(2, userId);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<HashMap> userMsgList = query.getResultList();
        Query query1 = em.createNativeQuery("  select " +
                "    MSG.user1 user1, MSG.user2 user2, count(MSG.message_id) as unopenCnt " +
                "    from message MSG, staff USR1, staff USR2 " +
                "    where " +
                "    ( MSG.user1 = ? or MSG.user2 = ? ) " +
                "    and MSG.status = '0' " +
                "    group by MSG.user1, MSG.user2" +
                "    order by MSG.user1, MSG.user2");
        query1.setParameter(1, userId);
        query1.setParameter(2, userId);
        query1.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<HashMap> unopenCntList = query1.getResultList();
        em.close();
        for (int i = 0; i < unopenCntList.size(); i++) {
            for (int j = 0; j < userMsgList.size(); j++) {
                if (unopenCntList.get(i).get("user1") == userMsgList.get(j).get("user1")
                        && unopenCntList.get(i).get("user2") == userMsgList.get(j).get("user2")) {
                    userMsgList.get(j).put("unopenCnt", unopenCntList.get(i).get("unopenCnt"));
                    break;
                }
            }
        }
        List<List<HashMap>> all = new ArrayList<List<HashMap>>();
        all.add(userMsgList);
        return all;
    }

    /**
     * 根据两个用户ID检索他们之间的聊天消息
     */
    public List<Message> selectMsgByUsers(Integer userId1, Integer userId2) {
        // 更新成已读状态
//        Message message = new Message();
//        message.setUser1(userId1);
//        message.setUser2(userId2);
//        message.setStatus("2");
//        mapper.saveAndFlush(message);
        return mapper.findByUser1AndUser2(userId1, userId2);

    }

}
