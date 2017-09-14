package com.sbaby.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.sbaby.common.entity.TestDubboEntry;
import com.sbaby.common.service.AsynService;

@Service(version="2.0.0",protocol={"dubbo"},retries=1,timeout=1200000)
public class AsynTestServiceImpl implements AsynService {

	@Override
	public TestDubboEntry asynTest() {
		System.out.println("------- asynTest() --------");
		try {
			/**
			 * 等待5秒，测试异步调用
			 */
			Thread.sleep(15000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("------- asynTest() end --------");
		TestDubboEntry entry = new TestDubboEntry();
		entry.setUserName("tomtang");
		// TODO Auto-generated method stub
		return entry;
	}

}
