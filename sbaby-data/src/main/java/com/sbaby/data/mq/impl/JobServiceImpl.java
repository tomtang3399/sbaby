package com.sbaby.data.mq.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbaby.common.entity.db.nosqlDB.cache.CacheJob;
import com.sbaby.common.entity.db.relationalDB.ElasticJob;
import com.sbaby.common.util.SbabyCommonService;
import com.sbaby.data.core.cache.CacheTemplate;
import com.sbaby.data.mq.IJobService;
import com.sbaby.data.reponstity.relationalDB.service.JobInfoReponstityService;

@Service
public class JobServiceImpl extends SbabyCommonService implements IJobService{

	@Autowired
	private JobInfoReponstityService jobInfoReponstityService;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private CacheTemplate cacheTemplate;
	
	@Override
	public void findAllJobInfo(Message message) {
		// TODO Auto-generated method stub
		Address replyTo = message.getMessageProperties().getReplyToAddress();
		List<ElasticJob> jobs = jobInfoReponstityService.findAll();
		amqpTemplate.convertAndSend(replyTo.getExchangeName(), replyTo.getRoutingKey(),
				binder.toJson(jobs));
	}

	@Override
	public void createJobCache(Message message) {
		// TODO Auto-generated method stub
		try {
			String objStr = new String(message.getBody(),"UTF-8");
			System.out.println("-----------createJobCache------------:"+objStr);
			CacheJob cacheJob = binder.fromJson(objStr, CacheJob.class);
			cacheTemplate.set(cacheJob.getJobName(), cacheJob);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void findJobCache(Message message) {
		// TODO Auto-generated method stub
		try {
			Address address = message.getMessageProperties().getReplyToAddress();
			String jobName = new String(message.getBody(),"UTF-8");
			CacheJob cacheJob = (CacheJob) cacheTemplate.get(jobName);
			
			amqpTemplate.convertAndSend(address.getExchangeName(), address.getRoutingKey(), binder.toJson(cacheJob));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
