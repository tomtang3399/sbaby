package com.sbaby.data.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 任务操作
 * @author Administrator
 *
 */
public interface IJobService {

	/**
	 * 从数据库获取所有的任务信息
	 * @param message
	 */
	@RabbitListener(queues="job.data",containerFactory="jobDataFactory")
	void findAllJobInfo(Message message);
	
	/**
	 * 缓存任务调度器
	 * @param message
	 */
	@RabbitListener(queues="job.cache.create",containerFactory="createJobCacheFactory")
	void createJobCache(Message message);
	
	/**
	 * 获取缓存中的任务调度器
	 * @param message
	 */
	@RabbitListener(queues="job.cache.find",containerFactory="findJobCacheFactory")
	void findJobCache(Message message);
}
