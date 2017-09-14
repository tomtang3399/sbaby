package com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic;

import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

public interface EsCommonReponstityService<T> {
	/**
	 * 查询Entity数量
	 * 
	 * @return
	 */
	public long count();

	/**
	 * 删除Entity
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 删除Entity
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 删除Entity集合
	 * 
	 * @param entities
	 */
	public void deleteInBatch(Iterable<T> entities);

	/**
	 * 根据Id判断Entity是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean exists(String id);

	/**
	 * 查询Entity
	 * 
	 * @param id
	 * @return
	 */
	public T find(String id);

	/**
	 * 查询所有Entity
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 保存Entity集合
	 * 
	 * @param entities
	 * @return
	 */
	public List<T> save(Iterable<T> entities);

	/**
	 * 保存Entity
	 * 
	 * @param entity
	 * @return
	 */
	public T saveAndFlush(T entity);
	
	/**
	 * 条件查询
	 * @param query
	 * @return
	 */
	public List<T> searchQuery(SearchQuery query);
	
}
