package com.lijie.controller;

import com.lijie.pojo.Message;
import com.lijie.pojo.MessageItem;
import com.lijie.service.MessageService;
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
	private MessageService messageService;

	/* 
	 * 用户发消息
	 */
	@RequestMapping("/send")
	public int send(@RequestBody Message msg) {
		return messageService.send(msg);
	}
	
	/* 
	 * 取得系统消息/用户消息一览
	 */
	@RequestMapping("/getMsgList")
	public List<List<Message>> getMsgList(@RequestParam("userId") Integer userId) {
		return messageService.selectAllByUser(userId);
	}
	
	/* 
	 * 取得两个用户间的消息一览
	 */
	@RequestMapping("/getMsg")
	public List<Message> getMsgInUsers(@RequestParam("userId1") Integer userId1, @RequestParam("userId2") Integer userId2) {
		return messageService.selectMsgByUsers(userId1, userId2);
	}

}
