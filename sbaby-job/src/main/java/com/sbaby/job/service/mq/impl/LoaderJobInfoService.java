package com.sbaby.job.service.mq.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobCoreConfiguration.Builder;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.sbaby.common.entity.db.nosqlDB.cache.CacheJob;
import com.sbaby.common.entity.db.relationalDB.ElasticJob;
import com.sbaby.common.util.SbabyCommonService;
import com.sbaby.job.service.mq.ILoaderJobInfoService;

@Service
public class LoaderJobInfoService extends SbabyCommonService implements ILoaderJobInfoService {

	@Autowired
	private CoordinatorRegistryCenter coordinatorRegistryCenter;
	
	@Autowired
	private AmqpTemplate template;
	
	@Override
	public void jobLoader(Message message) {
		// TODO Auto-generated method stub
		System.out.println("------------- begin jobLoader --------------");
		try {
			String content = "";
			MessageProperties properties = new MessageProperties();
			Message msg = new Message(content.getBytes(), properties);
			Message backMsg = template.sendAndReceive("jobExchange", "job.data", msg);
			String jobStr = new String(backMsg.getBody(), "UTF-8");
			List<ElasticJob> jobsConfig = binder.fromJson(jobStr, binder.contructCollectionType(List.class, ElasticJob.class));
			if (CollectionUtils.isNotEmpty(jobsConfig)) {
				for (ElasticJob param : jobsConfig) {
					try {
						
						/**
						 * 判定任务状态是否是开启，若是则需要装载job
						 */
						if (param.getEnable()) {
							createJobConfiguration(param.getJobName(), param.getCron(), param.getShardingTotalCount(),
									param.getShardingItemParameters(), Class.forName(param.getExecuteClass()));
						}
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public JobScheduler createJobConfiguration(String jobName, String cron, Integer shardingTotalCount,
			String shardingItemParameters, Class obj) {
		/**
		 * 定义作业核心配置 此处我定义了分片数为1，用来确保在集群中只有一个分片来处理该任务，如果分片数大于1，则需要在任务实现类里面，
		 * 根据shardingItemParameters不同的值做不同的处理（即把一个任务分割成了多个小任务），
		 * 比如此处我设置了shardingItemParameters的值为"0=a,1=b,2=c",同时分片为1个，则在任务实现类中，可以获得第一个分片（即0）对应的值
		 * ，获取方式为context.getShardingParameter()，值为a，若将任务切分为3个分片执行，则需要在任务执行类中以该值做switch
		 * case， 用来分别执行小任务的逻辑。 failover(true)表示开启任务失效转移，默认为false
		 */
		Builder builder = JobCoreConfiguration.newBuilder(jobName, cron,
				shardingTotalCount);
		if (StringUtils.isNotBlank(shardingItemParameters)) {
			builder.shardingItemParameters(shardingItemParameters);
		}
		JobCoreConfiguration simpleCoreConfig = builder.failover(true).build();
		// 定义SIMPLE类型配置
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig,
				obj.getCanonicalName());
		// 定义Lite作业根配置
		LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true)
				.monitorExecution(true).build();

		/**
		 * 初始化任务
		 */
		JobScheduler job = new JobScheduler(coordinatorRegistryCenter, simpleJobRootConfig);
		job.init();
		
		return job;
	}

}
