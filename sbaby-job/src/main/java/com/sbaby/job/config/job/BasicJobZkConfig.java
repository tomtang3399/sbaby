package com.sbaby.job.config.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

@Configuration
public class BasicJobZkConfig {

	@Value("${zookeeper.cluster.address}")
	private String zkHosts;

	@Value("${zookeeper.cluster.namespace}")
	private String zkNameSpace;
	
	/**
	 * 创建zk连接，建立注册中心
	 * 
	 * @return
	 */
	@Bean
	public CoordinatorRegistryCenter createRegistryCenter() {
		CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
				new ZookeeperConfiguration(zkHosts, zkNameSpace));
		regCenter.init();
		return regCenter;
	}
}
