package com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EsBasicReponstityService<T> extends ElasticsearchRepository<T, String>{
	
}
