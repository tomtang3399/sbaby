package com.sbaby.dubbo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.sbaby.common.entity.TestDubboEntry;
import com.sbaby.common.service.AsynService;
import com.sbaby.common.service.SyncService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TestController {

	@Reference(version="1.0.0")
	private SyncService service;
	
	/**
	 * async表示是否异步调用;sent表示是否等待返回结果，为true表示可用Future获取异步接口的值，为false表示异步发送消息后不接收结果
	 * 具体可参考
	 */
	@Reference(version="2.0.0", sent=true, async=true)
	private AsynService asycService;
	
	@RequestMapping(value="/demo", method=RequestMethod.GET)
	public String test() {
		TestDubboEntry entry = service.test();
		System.out.println(entry.getUserName());
		return "---------ok---------";
	}
	
	@RequestMapping(value="/asynDemo", method=RequestMethod.GET)
	public String asynTest() {
		TestDubboEntry entry = asycService.asynTest();
		Future<TestDubboEntry> pFuture = RpcContext.getContext().getFuture();
		
		/**
		 * 在pFuture.get()返回该次调用之前，可以穿插其他业务处理逻辑，提高处理效率
		 */
		
		try {
			/**
			 * 若配置sent=false,则此处会获取不到值因为他表示异步发送一条消息给另外一个模块,而不接受结果;同时，当消费端在处理的过程中，此处一直处于等待状态
			 */
			entry = pFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(entry.getUserName());
		return "---------ok---------";
	}
}
