package com.sbaby.middleware.config.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbaby.common.util.AMQPConnectionConfig;

@Configuration
public class UserQueueConfig extends AMQPConnectionConfig {

	/**
	 * 构建生产端的queue
	 * @return
	 */
	@Bean(name="userQueue") 
    public Queue fooQueue() {
		Queue queue = new Queue("user.test", false, false, false);
		return queue;
    }
	
	@Bean
	public DirectExchange exchange() {
		DirectExchange exchange = new DirectExchange("userExchange", false, false);
		return exchange;
	}
	
	/**
	 * 将queue与exchange进行绑定
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding binding(@Qualifier("userQueue") Queue queueMessage) {
		return BindingBuilder.bind(queueMessage).to(exchange()).with("user.test");
	}
	
	/**
	 * 设置该队列的消费端的个数,可屏蔽,屏蔽后默认为1
	 * @param connectionFactory
	 * @return
	 */
	@Bean(name="userFactory")
	public SimpleRabbitListenerContainerFactory loginFactory(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		factory.setMaxConcurrentConsumers(5);
		factory.setConcurrentConsumers(3);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}
