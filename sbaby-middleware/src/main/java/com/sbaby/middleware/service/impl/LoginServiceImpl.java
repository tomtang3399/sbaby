package com.sbaby.middleware.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.common.service.UserSyncService;
import com.sbaby.common.util.AMQPService;
import com.sbaby.middleware.service.LoginService;

@Service
public class LoginServiceImpl extends AMQPService implements LoginService {

	@Reference(version="sbaby.userSyncService", check = false)
	protected UserSyncService userSyncService;
	
	/**
	 * 通过rabbitMQ获取数据
	 */
	public String loginSendMsg() {
//		String content = "hello";
//		MessageProperties properties = new MessageProperties();
//		Message msg = new Message(content.getBytes(), properties);
		System.out.println(System.currentTimeMillis());
		
		/**
		 * 同步模式
		 */
//		mqTemplate.sendAndReceive("login.test", "login.test", msg);
		
		/**
		 * 异步模式
		 */
		convertAndSend("loginExchange", "login.test", "aaaa");
		convertAndSend("userExchange", "user.test", "bbbb");
		System.out.println(System.currentTimeMillis());
		
		return "----- ok -----";
	}

	/**
	 * 通过dubbo PRC同步模式获取数据
	 */
	@Override
	public String findAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userSyncService.findAllUsers();
		String msg = binder.toJson(users);
		return msg;
	}
	
}
