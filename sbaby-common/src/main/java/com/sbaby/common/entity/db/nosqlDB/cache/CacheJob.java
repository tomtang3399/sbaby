package com.sbaby.common.entity.db.nosqlDB.cache;

import com.dangdang.ddframe.job.lite.api.JobScheduler;

/**
 * 用来缓存任务对应的调度器
 * @author Administrator
 *
 */
public class CacheJob {
	
	private JobScheduler job;
	
	private String jobName;

	public JobScheduler getJob() {
		return job;
	}

	public void setJob(JobScheduler job) {
		this.job = job;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
}
