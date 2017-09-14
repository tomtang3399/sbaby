package com.sbaby.data.reponstity.nosqlDB.elasticsearch.db;

import java.util.List;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.Province;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic.EsBasicReponstityService;

public interface EsProvinceReponstity extends EsBasicReponstityService<Province> {
	
	/**
	 * 利用spring-data-jpa方式操作ES数据库，该方式可自动解析方法名称自动转化为查询语句，注意查询方法的命名规范
	 * 操作及命名规范参考：https://es.yemengying.com/4/4.4/4.4.2.html
	 * @param provinceName
	 * @return
	 */
	List<Province> findByProvinceName(String provinceName);
	
	/**
	 * 利用spring-data-jpa方式操作ES数据库，该方式可自动解析方法名称自动转化为查询语句，注意查询方法的命名规范
	 * 操作及命名规范参考：https://es.yemengying.com/4/4.4/4.4.2.html
	 * @param provinceName
	 * @return
	 */
	List<Province> findByProvinceNameOrderByProvinceNameAsc(String provinceName);

}
