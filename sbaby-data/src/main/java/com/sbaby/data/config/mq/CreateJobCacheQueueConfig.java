package com.sbaby.data.config.mq;

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
public class CreateJobCacheQueueConfig extends AMQPConnectionConfig {
	
	/**
	 * 构建生产端的queue
	 * @return
	 */
	@Bean(name="createJobCacheQueue") 
    public Queue fooQueueCreateJobCacheQueue() {
		Queue queue = new Queue("job.cache.create", false, false, false);
		return queue;
    }
	
	/**
	 * 将queue与exchange进行绑定
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingCreateJobCacheQueue(@Qualifier("createJobCacheQueue") Queue queueMessage) {
		return BindingBuilder.bind(queueMessage).to(exchange()).with("job.cache.create");
	}
	
	/**
	 * 设置该队列的消费端的个数,可屏蔽,屏蔽后默认为1
	 * @param connectionFactory
	 * @return
	 */
	@Bean(name="createJobCacheFactory")
	public SimpleRabbitListenerContainerFactory loginFactoryCreateJobCacheQueue(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		factory.setMaxConcurrentConsumers(5);
		factory.setConcurrentConsumers(2);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}
