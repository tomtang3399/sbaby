package com.sbaby.data.service;

import java.util.List;

import com.sbaby.common.entity.db.nosqlDB.elasticSearch.Province;
import com.sbaby.common.entity.db.relationalDB.User;

public interface ICommonService {

	
	List<User> findAll();
	
	User saveUser();
	
	Province saveprovince();
	
//	List<Province> moreLikeByContent(String content);
	
//	List<Province> findByProvinceNameAsc(String provinceName);
}
