package com.lijie.service;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;

import java.util.List;

public interface MessageService {
    List<MessageItem> selectMsgByUsers(Integer userId1, Integer userId2);

    List<List<MessageItem>> selectAllByUser(Integer userId);

    int send(Message msg);
}
