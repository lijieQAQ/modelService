package com.lijie.service.impl;

import com.lijie.Dao.MessageDao;
import com.lijie.Util.ConstantField;
import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import com.lijie.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao mapper;

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
        if(message != null) {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 根据用户ID检索消息
     */
    public List<List<Message>> selectAllByUser(Integer userId) {


        // 用户最新消息
        List<Message> userMsgList = mapper.findByUser2OrderBySendTime(userId);
        // 用户未读消息数
        List<Message> unopenCntList = mapper.findByUser2AndStatusOrderBySendTime(userId, "1");
        for (int i=0; i < unopenCntList.size(); i++) {
            for (int j=0; j < userMsgList.size(); j++) {
                if (unopenCntList.get(i).getUser1() == userMsgList.get(j).getUser1()
                        && unopenCntList.get(i).getUser2() == userMsgList.get(j).getUser2() ) {
                    userMsgList.get(j).setUnopenCnt(unopenCntList.get(i).getUnopenCnt());
                    break;
                }
            }
        }

        List<List<Message>> all = new ArrayList<List<Message>>();
        all.add(userMsgList);

        return all;
    }

    /**
     * 根据两个用户ID检索他们之间的聊天消息
     */
    public List<Message> selectMsgByUsers(Integer userId1, Integer userId2) {
        // 更新成已读状态
        Message message = new Message();
        message.setUser1(userId1);
        message.setUser2(userId2);
        message.setStatus("2");
        mapper.saveAndFlush(message);
        return mapper.findByUser1AndUser2OrderBySendTime(userId1, userId2);

    }

}
