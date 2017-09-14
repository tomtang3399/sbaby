package com.sbaby.middleware.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface ILoginMQService {

	@RabbitListener(queues="login.test",containerFactory="loginFactory")
	void login(Message message);
	
	@RabbitListener(queues="user.test",containerFactory="userFactory")
	void user(Message message);
}
