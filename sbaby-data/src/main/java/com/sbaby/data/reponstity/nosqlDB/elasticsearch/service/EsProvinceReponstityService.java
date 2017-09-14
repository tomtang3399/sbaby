package com.sbaby.data.reponstity.nosqlDB.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.Province;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic.EsCommonReponstityServiceImpl;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.db.EsProvinceReponstity;

@Service
public class EsProvinceReponstityService extends EsCommonReponstityServiceImpl<Province, EsProvinceReponstity> {

	@Autowired
	public EsProvinceReponstity esProvinceReponstity;
	
	@Override
	protected EsProvinceReponstity getReponstity() {
		return esProvinceReponstity;
	}
	
	/**
	 * 利用spring-data-jpa方式操作ES数据库，该方式可自动解析方法名称自动转化为查询语句，注意查询方法的命名规范
	 * 命名规范参考：https://es.yemengying.com/4/4.4/4.4.2.html
	 * @param provinceName
	 * @return
	 */
	public List<Province> findByProvinceNameAsc(String provinceName) {
		List<Province> provinces = esProvinceReponstity.findByProvinceNameOrderByProvinceNameAsc(provinceName);
		return provinces;
	}

}
