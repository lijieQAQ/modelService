package com.lijie.controller;

import com.lijie.Dao.MessageMapper;
import com.lijie.Util.ConstantField;
import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MessageLogic {

	@Autowired
	private MessageMapper mapper;
	
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
		
		return mapper.insertSelective(msg);
	}
	
	/**
	 * 根据用户ID检索消息
	 */
	public List<List<MessageItem>> selectAllByUser(Integer userId) {
		
		// 系统消息
		List<MessageItem> sysMsgList = mapper.selectSysMsgByUser(userId);
		
		// 用户最新消息
		List<MessageItem> userMsgList = mapper.selectLatestMsgByUser(userId);
		// 用户未读消息数
		List<MessageItem> unopenCntList = mapper.selectUnopenCntByUser(userId);
		for (int i=0; i < unopenCntList.size(); i++) {
			for (int j=0; j < userMsgList.size(); j++) {
				if (unopenCntList.get(i).getUser1() == userMsgList.get(j).getUser1() 
					&& unopenCntList.get(i).getUser2() == userMsgList.get(j).getUser2() ) {
					userMsgList.get(j).setUnopenCnt(unopenCntList.get(i).getUnopenCnt());
					break;
				}
			}
		}
		
		List<List<MessageItem>> all = new ArrayList<List<MessageItem>>();
		all.add(sysMsgList);
		all.add(userMsgList);
		
		return all;
	}
	
	/**
	 * 根据两个用户ID检索他们之间的聊天消息
	 */
	public List<MessageItem> selectMsgByUsers(Integer userId1, Integer userId2) {
	
		// 更新成已读状态
		mapper.updateStatusByUsers(userId1, userId2);
		return mapper.selectMsgByUsers(userId1, userId2);
		
	}
	

}