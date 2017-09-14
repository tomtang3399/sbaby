package com.sbaby.common.util;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConnectionConfig {

	@Value("${mq.host}")
	private String host;
	
	@Value("${mq.port}")
	private Integer port;
	
	@Value("${mq.username}")
	private String username;
	
	@Value("${mq.password}")
	private String password;
	
	@Bean(name="defaultConnectionFactory")
    protected ConnectionFactory firstConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(true);//消息是否回调，如需要mq的rpc同步模式，则需要设置为true
        return connectionFactory;
    }
	
	@Bean
	public DirectExchange exchange() {
		DirectExchange exchange = new DirectExchange("jobExchange", false, false);
		return exchange;
	}
	
}
