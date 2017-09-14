package com.sbaby.job.service.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 异步操作job
 * @author Administrator
 *
 */
public interface ILoaderJobInfoService {

	/**
	 * 异步加载任务、操作缓存
	 * @param message
	 */
	@RabbitListener(queues="job.loader",containerFactory="loaderJobFactory")
	void jobLoader(Message message);
}
