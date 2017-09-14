package com.sbaby.middleware.mq.impl;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import com.sbaby.middleware.mq.ILoginMQService;

@Service
public class LoginMQServiceImpl implements ILoginMQService {

	@Override
	public void login(Message message) {
		// TODO Auto-generated method stub
		try {
			String msg = new String(message.getBody(), "UTF-8");
			System.out.println("-------login------:"+msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void user(Message message) {
		// TODO Auto-generated method stub
		try {
			String msg = new String(message.getBody(), "UTF-8");
			System.out.println("-------user------:"+msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
