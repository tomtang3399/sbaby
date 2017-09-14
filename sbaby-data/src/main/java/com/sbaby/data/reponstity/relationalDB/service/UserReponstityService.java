package com.sbaby.data.reponstity.relationalDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.data.reponstity.relationalDB.basic.CommonReponstityServiceImpl;
import com.sbaby.data.reponstity.relationalDB.db.UserReponstity;

@Service
@Transactional
public class UserReponstityService extends CommonReponstityServiceImpl<User, UserReponstity> {

	@Autowired
	public UserReponstity userReponstity;
	
	@Override
	protected UserReponstity getReponstity() {
		return userReponstity;
	}
}
