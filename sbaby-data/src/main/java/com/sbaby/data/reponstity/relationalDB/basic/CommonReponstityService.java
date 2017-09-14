package com.sbaby.data.reponstity.relationalDB.basic;

import java.util.List;
import java.util.Map;

public interface CommonReponstityService<T> {
	/**
	 * 查询Entity数量
	 * 
	 * @return
	 */
	public long count();

	/**
	 * 查询Entity数量
	 * 
	 * @param searchParams
	 * @return
	 */
	public long count(Map<String, Object> searchParams);

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
	 * 查询Entity数量
	 * 
	 * @param searchParams
	 * @return
	 */
	public T find(Map<String, Object> searchParams);

	/**
	 * 查询所有Entity
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 查询所有Entity
	 * 
	 * @param searchParams
	 * @return
	 */
	public List<T> findAll(Map<String, Object> searchParams);

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
	
}
