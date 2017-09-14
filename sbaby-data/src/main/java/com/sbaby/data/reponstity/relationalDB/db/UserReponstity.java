package com.sbaby.data.reponstity.relationalDB.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.data.reponstity.relationalDB.basic.BasicReponstityService;

public interface UserReponstity extends BasicReponstityService<User> {

	/**
	 * nativeQuery为true表示使用原生sql, #{#entityName}对应的值是User实体类的@Entry值, 要么@Entry值指定为数据库名称要么不填
	 * 参考http://www.cnblogs.com/zj0208/p/6008627.html
	 * @param userName
	 * @return
	 */
	@Query(value="select * from #{#entityName} where userName = ?1",nativeQuery=true)
	List<User> findByUserName(String userName);
}
