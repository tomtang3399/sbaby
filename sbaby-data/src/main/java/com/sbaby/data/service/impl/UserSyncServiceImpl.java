package com.sbaby.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.common.service.UserSyncService;
import com.sbaby.data.reponstity.relationalDB.service.UserReponstityService;

/**
 * retries 表示重试次数
 * timeout 表示超时时间
 * @author Administrator
 *
 */
@Service(version="sbaby.userSyncService", protocol="dubbo", retries=1, timeout=1200000)
public class UserSyncServiceImpl implements UserSyncService {
	
	@Autowired
	private UserReponstityService userReponstityService;

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userReponstityService.findAll();
		return users;
	}
	
	
}
