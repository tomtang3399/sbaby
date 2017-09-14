package com.sbaby.common.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AMQPService")
public class AMQPService extends SbabyCommonService {

	@Autowired
	public AmqpTemplate amqpTemplate;

	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * 
	 * @param routingKey
	 * @param obj
	 * @return
	 */
	public void convertAndSend(String routingKey, Object obj) {
		cast(routingKey, routingKey, obj);
	}
	

	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * 
	 * @param exchangeName
	 * @param routingKey
	 * @param obj
	 */
	public void convertAndSend(String exchangeName, String routingKey, Object obj) {
		cast(exchangeName, routingKey, routingKey, obj);
	}

	/**
	 * 向Rabbit MQ服务器发送对象,并等待返回参数.(同步,RPC模式)
	 * 
	 * @param routingKey
	 * @param obj
	 * @return
	 */
	public String sendMsgAndReceive(String routingKey, Object obj) {
		return call(routingKey, routingKey, obj);
	}
	
	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * @param routingKey
	 * @param methodMappingKey 指定处理此消息的方法
	 * @param obj
	 */
	public void cast(String routingKey, String methodMappingKey, Object obj) {
		amqpTemplate.convertAndSend(routingKey, obj, getMessagePropertiesSetter(methodMappingKey));
	}
	

	/**
	 * 向Rabbit MQ服务器发送对象.(异步)
	 * 
	 * @param exchangeName
	 * @param routingKey
	 * @param methodMappingKey 指定处理此消息的方法
	 * @param obj
	 */
	public void cast(String exchangeName, String routingKey, String methodMappingKey, Object obj) {
		amqpTemplate.convertAndSend(exchangeName, routingKey, obj, getMessagePropertiesSetter(methodMappingKey));
	}

	/**
	 * 向Rabbit MQ服务器发送对象,并等待返回参数.(同步,RPC模式)
	 * 
	 * @param routingKey
	 * @param obj
	 * @param methodMappingKey 指定处理此消息的方法
	 * @return
	 */
	public String call(String routingKey, String methodMappingKey, Object obj) {
		return (String) amqpTemplate.convertSendAndReceive(routingKey, obj, getMessagePropertiesSetter(methodMappingKey));
	}
	
	private static MessagePropertiesSetter getMessagePropertiesSetter(String methodMappingKey) {
		MessagePropertiesSetter setter = new MessagePropertiesSetter();
		setter.setMethodMappingKey(methodMappingKey);
		return setter;
	}
	
	/**
	 * 此类用于设置消息的属性
	 * @author think
	 *
	 */
	static class MessagePropertiesSetter implements MessagePostProcessor {
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		
		public MessagePropertiesSetter() {
		}
		
		public void setMethodMappingKey(String methodMappingKey) {
			mapData.put("method", methodMappingKey);
		}
		
		@Override
		public Message postProcessMessage(Message message) throws AmqpException {
			MessageProperties propes = message.getMessageProperties();
			Map<String, Object> headers = propes.getHeaders();
			
			mapData.put("APIRequestCode", "test");
			
			headers.putAll(mapData);
			
			return message;
		}
		
	}

}
