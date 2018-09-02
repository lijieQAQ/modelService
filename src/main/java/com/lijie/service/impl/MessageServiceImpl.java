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
                "    MSG.MESSAGE_ID messageId, MSG.MSG_TYPE msgType, MSG.USER1 user1, MSG.USER2 user2, MSG.SPEAKER speaker, MSG.MESSAGE message, MSG.SEND_TIME sendTime, MSG.OPEN_TIME openTime, MSG.STATUS status, " +
                "    MSG.DEL_FLG delFlg, MSG.CREATE_ID createId, MSG.CREATE_DATE createDate, MSG.UPDATE_ID updateId, MSG.UPDATE_DATE updateDate, " +
                "    USR1.NAME AS userName1, USR1.avatar AS portrait1, USR2.NAME AS userName2, USR2.avatar AS portrait2, " +
                "    0 AS unopenCnt " +
                "    from MESSAGE MSG, staff USR1, staff USR2, " +
                "    ( select USER1,  USER2, MAX(SEND_TIME) AS SEND_TIME " +
                "      from MESSAGE  " +
                "      where ( USER1 = ? or USER2 = ? ) " +
                "      group by USER1 asc, USER2 asc ) AS A " +
                "    where MSG.USER1 = A.USER1 and MSG.USER2 = A.USER2 and MSG.SEND_TIME = A.SEND_TIME AND MSG.USER1 = USR1.id" +
                "  AND MSG.USER2 = USR2.id" +
                "    order by MSG.USER1, MSG.USER2");
        query.setParameter(1, userId);
        query.setParameter(2, userId);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<HashMap> userMsgList = query.getResultList();
        Query query1 = em.createNativeQuery("  select " +
                "    MSG.USER1 user1, MSG.USER2 user2, count(MSG.MESSAGE_ID) as unopenCnt " +
                "    from MESSAGE MSG, STAFF USR1, STAFF USR2 " +
                "    where " +
                "    ( MSG.USER1 = ? or MSG.USER2 = ? ) " +
                "    and MSG.STATUS = '0' " +
                "    group by MSG.USER1, MSG.USER2" +
                "    order by MSG.USER1, MSG.USER2");
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
