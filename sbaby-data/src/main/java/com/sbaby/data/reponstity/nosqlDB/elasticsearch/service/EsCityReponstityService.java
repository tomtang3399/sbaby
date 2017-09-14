package com.sbaby.data.reponstity.nosqlDB.elasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.City;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic.EsCommonReponstityServiceImpl;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.db.EsCityReponstity;

@Service
public class EsCityReponstityService extends EsCommonReponstityServiceImpl<City, EsCityReponstity> {

	@Autowired
	public EsCityReponstity esCityReponstity;
	
	@Override
	protected EsCityReponstity getReponstity() {
		return esCityReponstity;
	}

}
