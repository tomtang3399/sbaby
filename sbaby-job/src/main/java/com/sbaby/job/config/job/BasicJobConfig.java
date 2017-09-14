package com.sbaby.job.config.job;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicJobConfig {

	@Autowired
	private AmqpTemplate template;

	/**
	 * 异步加载任务
	 * @return
	 */
	@Bean
	public Boolean loaderJobFromDb() {
		Boolean bool = false;
		try {
			template.convertAndSend("jobExchange", "job.loader", new Message("".getBytes(), new MessageProperties()));
			bool = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bool;
	}

}
