package com.sbaby.data.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.Province;
import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.common.service.UserSyncService;
import com.sbaby.data.service.ICommonService;

@RestController
public class CommonController {

	@Autowired
	private ICommonService commonService;
	
	/**
	 * 当一个项目既充当消费者又充当生产者时，需要关闭启动时检查，机设置check=false;因为默认是启动时检查消费者是否对应有生产者，
	 * 即消费者依赖生产者，但是一个项目既充当消费者又充当生产者时在启动时会报消费者找不到对应的生产者（因为生产者正在加载中）的异常，
	 * 当关闭启动检查时，可绕过该异常，同时，在项目启动完全时，生产者、消费者都是匹配对应的，也就不会抛出异常
	 */
	@Reference(version="sbaby.userSyncService", check=false)
	private UserSyncService userSyncService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String findUsers() {
		List<User> users = commonService.findAll();
		return "------1------";
	}
	
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	public String saveUsers() {
		User user = commonService.saveUser();
		return "------2------";
	}
	
	@RequestMapping(value="/saveprovince", method=RequestMethod.POST)
	public String saveprovince() {
		Province province = commonService.saveprovince();
		return "--------3------";
	}
	
//	@RequestMapping(value="/findProvince", method=RequestMethod.GET)
//	public String findProvince(@RequestParam(value="provinceName", required=false) String provinceName ) {
//		List<Province> province = commonService.findByProvinceNameAsc(provinceName);
//		return JSONArray.toJSONString(province);
//	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		List<User> users = userSyncService.findAllUsers();
		System.out.println(users.size());
		return "--------ok------";
	}
}
