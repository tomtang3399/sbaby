package com.sbaby.data.reponstity.relationalDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sbaby.common.entity.db.relationalDB.ElasticJob;
import com.sbaby.data.reponstity.relationalDB.basic.CommonReponstityServiceImpl;
import com.sbaby.data.reponstity.relationalDB.db.JobInfoReponstity;

@Service
@Transactional
public class JobInfoReponstityService extends CommonReponstityServiceImpl<ElasticJob, JobInfoReponstity> {

	@Autowired
	public JobInfoReponstity jobInfoReponstity;
	
	@Override
	protected JobInfoReponstity getReponstity() {
		return jobInfoReponstity;
	}
}
