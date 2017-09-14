package com.sbaby.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.sbaby.common.entity.TestDubboEntry;
import com.sbaby.common.service.AsynService;
import com.sbaby.common.service.SyncService;

@Service(version="1.0.0",protocol={"dubbo"},retries=1,timeout=1200000)
public class TestServiceImpl implements SyncService {

	@Override
	public TestDubboEntry test() {
		// TODO Auto-generated method stub
		System.out.println("------- test() --------");
		TestDubboEntry entry = new TestDubboEntry();
		entry.setUserName("tomtang");
		return entry;
	}

}
