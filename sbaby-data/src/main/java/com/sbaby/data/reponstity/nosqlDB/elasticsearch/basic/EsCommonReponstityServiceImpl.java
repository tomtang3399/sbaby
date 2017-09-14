package com.sbaby.data.reponstity.nosqlDB.elasticsearch.basic;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.google.common.collect.Lists;

public abstract class EsCommonReponstityServiceImpl<T, R extends EsBasicReponstityService<T>> 
	implements EsCommonReponstityService<T> {
	
	protected abstract R getReponstity();
	
	/**
	 * 查询Entity数量
	 * 
	 * @return
	 */
	public long count() {
		return getReponstity().count();
	}

	/**
	 * 删除Entity
	 * 
	 * @param id
	 */
	public void delete(String id) {
		getReponstity().delete(id);
	}

	/**
	 * 删除Entity
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		getReponstity().delete(entity);
	}

	/**
	 * 删除Entity集合
	 * 
	 * @param entities
	 */
	public void deleteInBatch(Iterable<T> entities) {
		getReponstity().delete(entities);
	}

	/**
	 * 根据Id判断Entity是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean exists(String id) {
		return getReponstity().exists(id);
	}

	/**
	 * 查询Entity
	 * 
	 * @param id
	 * @return
	 */
	public T find(String id) {
		return getReponstity().findOne(id);
	}

	/**
	 * 查询所有Entity
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return iterableToList(getReponstity().findAll());
	}

	/**
	 * 保存Entity集合
	 * 
	 * @param entities
	 * @return
	 */
	public List<T> save(Iterable<T> entities) {
		return iterableToList(getReponstity().save(entities));
	}

	/**
	 * 保存Entity
	 * 
	 * @param entity
	 * @return
	 */
	public T saveAndFlush(T entity) {
		return getReponstity().save(entity);
	}
	
	/**
	 * 普通分页分页
	 * @param page
	 * @param pageNum
	 * @param direction
	 * @param directionProperty
	 * @return
	 */
	public Pageable ordinaryPageable(int page, int pageNum, Direction direction, String...directionProperty) {
		PageRequest pagerequest = new PageRequest(page, pageNum, direction, directionProperty);
		return pagerequest;
	}
	
	/**
	 * 普通分页
	 * @param page：当前页数
	 * @param pageNum：一页显示的条数
	 * @param direction：排序asc或者desc
	 * @param directionProperty：排序的字段
	 * @return
	 */
	public List<T> ordinaryPage(int page, int pageNum, Direction direction, String... directionProperty) {
		Page<T> pages = getReponstity().findAll(ordinaryPageable(page, pageNum, direction, directionProperty));
		return pages.getContent();
	}
	
	/**
	 * Iterable转list
	 * @param li
	 * @return
	 */
	public List<T> iterableToList(Iterable<T> li) {
		List<T> list = Lists.newArrayList();
		li.forEach(single ->{list.add(single);});
		return list;
	}
	
	/**
	 * 条件查询
	 * @param query
	 * @return
	 */
	public List<T> searchQuery(SearchQuery query) {
		return getReponstity().search(query).getContent();
	}
	
}
