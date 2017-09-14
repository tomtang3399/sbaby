package com.sbaby.data.reponstity.relationalDB.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.sbaby.data.utils.SearchFilter;

public abstract class CommonReponstityServiceImpl<T, R extends BasicReponstityService<T>> implements CommonReponstityService<T> {
	
	protected abstract R getReponstity();
	
	@SuppressWarnings("unchecked")
	private Specification<T> buildSpecification(Map<String, Object> searchParams) {
		return new Specification<T>() {

			@SuppressWarnings({"rawtypes" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				
				Map<String, SearchFilter> filterMap = SearchFilter.parse(searchParams);
				
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Collection<SearchFilter> collection = filterMap.values();
				
				for (SearchFilter filter : collection) {
					// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
					String[] names = StringUtils.split(filter.fieldName, ".");
					Path expression = root.get(names[0]);
					for (int i = 1; i < names.length; i++) {
						expression = expression.get(names[i]);
					}
					
					System.out.println("------------path:"+expression.toString());
					
					switch(filter.operator){
						case EQ:
							predicates.add(cb.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(cb.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(cb.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(cb.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(cb.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(cb.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case NOT:
							predicates.add(cb.notEqual(expression, (Comparable) filter.value));
							break;
						case IsNull:
							predicates.add(cb.isNull(expression));
							break;
						case NotNull:
							predicates.add(cb.isNotNull(expression));
							break;
					}
				}
				
				// 将所有条件用 and 联合起来
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				}
				
				
				return cb.conjunction();
			}
			
		};
	}
	
	/**
	 * 查询Entity数量
	 * 
	 * @return
	 */
	public long count() {
		return getReponstity().count();
	}

	/**
	 * 查询Entity数量
	 * 
	 * @param searchParams
	 * @return
	 */
	public long count(Map<String, Object> searchParams) {
		return getReponstity().count(buildSpecification(searchParams));
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
		getReponstity().deleteInBatch(entities);
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
	 * 查询Entity数量
	 * 
	 * @param searchParams
	 * @return
	 */
	public T find(Map<String, Object> searchParams) {
		return getReponstity().findOne(buildSpecification(searchParams));
	}

	/**
	 * 查询所有Entity
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return getReponstity().findAll();
	}

	/**
	 * 查询所有Entity
	 * 
	 * @param searchParams
	 * @return
	 */
	public List<T> findAll(Map<String, Object> searchParams) {
		return getReponstity().findAll(buildSpecification(searchParams));
	}

	/**
	 * 保存Entity集合
	 * 
	 * @param entities
	 * @return
	 */
	public List<T> save(Iterable<T> entities) {
		return getReponstity().save(entities);
	}

	/**
	 * 保存Entity
	 * 
	 * @param entity
	 * @return
	 */
	public T saveAndFlush(T entity) {
		return getReponstity().saveAndFlush(entity);
	}
	
	/**
	 * 普通分页分页
	 * @param page
	 * @param pageNum
	 * @param direction
	 * @param directionProperty
	 * @return
	 */
	private Pageable ordinaryPageable(int page, int pageNum, Direction direction, String...directionProperty) {
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
	
}
