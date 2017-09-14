package com.sbaby.job.service.job;

import com.dangdang.ddframe.job.api.ShardingContext;

public class SimpleDemoJob implements com.dangdang.ddframe.job.api.simple.SimpleJob {

	/**
	 * 当一个任务可以被切成若干有先后顺序的小任务时（比如2个小任务），elastic-job自动按分片大小顺序执行，
	 * 可以将该任务分片（分片数量等于小任务数量，且最好为服务器数量的整数倍，具体的分片见配置文件）
	 */
	@Override
	public void execute(ShardingContext context) {
		// TODO Auto-generated method stub
		String shardingParameter = context.getShardingParameter();
		switch (shardingParameter) {
			case "a":
				System.out.println("--------solt 1-----------");
				break;
			case "b":
				System.out.println("--------solt 2-----------");
				break;
			case "c":
				System.out.println("--------solt 3-----------");
				break;
			default:
				break;
		}
		System.out.println(System.currentTimeMillis()+"-----"+shardingParameter);
	}

}
