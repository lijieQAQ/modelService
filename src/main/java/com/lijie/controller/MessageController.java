package com.lijie.controller;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
	

	@Autowired
	private MessageLogic logic;

	/* 
	 * 用户发消息
	 */
	@RequestMapping("/send")
	public int send(@RequestBody Message msg) {
		return logic.send(msg);
	}
	
	/* 
	 * 取得系统消息/用户消息一览
	 */
	@RequestMapping("/getMsgList")
	public List<List<MessageItem>> getMsgList(@RequestParam("userId") Integer userId) {
		return logic.selectAllByUser(userId);
	}
	
	/* 
	 * 取得两个用户间的消息一览
	 */
	@RequestMapping("/getMsg")
	public List<MessageItem> getMsgInUsers(@RequestParam("userId1") Integer userId1, @RequestParam("userId2") Integer userId2) {
		return logic.selectMsgByUsers(userId1, userId2);
	}

}
