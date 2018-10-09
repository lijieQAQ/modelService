package com.lijie.controller;

import com.lijie.Util.PushExample;
import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import com.lijie.pojo.Staff;
import com.lijie.service.MessageService;
import com.lijie.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
	

	@Autowired
	private MessageService messageService;
	@Autowired
	private StaffService staffService;

	/* 
	 * 用户发消息
	 */
	@RequestMapping("/send")
	public int send(Message msg, String content) {
		Staff staff1 = staffService.findById(msg.getUser1());
		Staff staff2 = staffService.findById(msg.getUser2());
		msg.setUserName1(staff1.getName());
		msg.setUserName2(staff2.getName());
		msg.setPortrait1(staff1.getAvatar());
		msg.setPortrait2(staff2.getAvatar());
		if(msg.getSpeaker() == msg.getUser1()) {
			PushExample.testSendPush(new String().valueOf(msg.getUser2()), content, msg.getUserName2());
		} else {
			PushExample.testSendPush(new String().valueOf(msg.getUser1()), content, msg.getUserName2());
		}
		msg.setMessage(content);
		return messageService.send(msg);
	}
	
	/* 
	 * 取得系统消息/用户消息一览
	 */
	@RequestMapping("/getMsgList")
	public List<List<HashMap>> getMsgList(Integer userId) {
		return messageService.selectAllByUser(userId);
	}
	
	/* 
	 * 取得两个用户间的消息一览
	 */
	@RequestMapping("/getMsg")
	public List<Message> getMsgInUsers(Integer userId1, Integer userId2) {
		return messageService.selectMsgByUsers(userId1, userId2);
	}

}
