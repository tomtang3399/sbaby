package com.sbaby.job.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dangdang.ddframe.job.lite.internal.schedule.JobRegistry;

@RestController
public class JobController {

	/**
	 * 测试暂停job,为了方便测试先用get方式
	 * @return
	 */
	@RequestMapping(value="/stopJob", method=RequestMethod.GET)
	public String stopJobDemo(@RequestParam(value="jobName") String jobName) {
		try {
			
//			JobRegistry.getInstance().shutdown(jobName);
			JobRegistry.getInstance().getJobScheduleController(jobName).pauseJob();
			System.out.println("stopJob end:"+System.currentTimeMillis());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "--------ok shut down--------";
	}
	
	/**
	 * 测试恢复job,为了方便测试先用get方式
	 * 在恢复时会立即执行一次，然后以定义的频率执行任务
	 * @return
	 */
	@RequestMapping(value="/startJob", method=RequestMethod.GET)
	public String startJobDemo(@RequestParam(value="jobName") String jobName) {
		try {
			System.out.println("startJob begin:"+System.currentTimeMillis());
			JobRegistry registry = JobRegistry.getInstance();
			registry.getJobScheduleController(jobName).resumeJob();
			System.out.println("startJob end:"+System.currentTimeMillis());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "--------ok begin--------";
	}
}
