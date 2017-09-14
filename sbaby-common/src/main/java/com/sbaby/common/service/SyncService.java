package com.sbaby.common.service;

import com.sbaby.common.entity.TestDubboEntry;

/**
 * dubbo同步调用示例
 * @author Administrator
 *
 */
public interface SyncService {

	TestDubboEntry test();
	
}
