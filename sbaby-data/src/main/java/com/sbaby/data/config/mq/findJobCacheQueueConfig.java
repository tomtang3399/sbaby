package com.sbaby.data.config.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sbaby.common.util.AMQPConnectionConfig;

@Configuration
public class findJobCacheQueueConfig extends AMQPConnectionConfig {
	
	/**
	 * 构建生产端的queue
	 * @return
	 */
	@Bean(name="findJobCacheQueue") 
    public Queue fooQueueFindJobCacheQueue() {
		Queue queue = new Queue("job.cache.find", false, false, false);
		return queue;
    }
	
	/**
	 * 将queue与exchange进行绑定
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	public Binding bindingFindJobCacheQueue(@Qualifier("findJobCacheQueue") Queue queueMessage) {
		return BindingBuilder.bind(queueMessage).to(exchange()).with("job.cache.find");
	}
	
	/**
	 * 设置该队列的消费端的个数,可屏蔽,屏蔽后默认为1
	 * @param connectionFactory
	 * @return
	 */
	@Bean(name="findJobCacheFactory")
	public SimpleRabbitListenerContainerFactory loginFactoryFindJobCacheQueue(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setAcknowledgeMode(AcknowledgeMode.NONE);
		factory.setMaxConcurrentConsumers(5);
		factory.setConcurrentConsumers(1);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}
}