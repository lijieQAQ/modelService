package com.lijie.service;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;

import java.util.HashMap;
import java.util.List;

public interface MessageService {
    List<Message> selectMsgByUsers(Integer userId1, Integer userId2);

    List<List<HashMap>> selectAllByUser(Integer userId);

    int send(Message msg);
}
