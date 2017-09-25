package com.sbaby.common.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheTemplate implements ICacheTemplate {

	@Autowired
	private RedisTemplate<String, Object> template;
	
	/**
	 * 向缓存中压入KEY-Value键值对
	* @Title: set  
	* @Description:   向缓存中压入KEY-Value键值对，若KEY已经存在，则覆盖
	* @param key
	* @param value void    返回类型  
	* @throws
	 */
	@Override
	public void set(String key, Object value)
	{
		 template.opsForValue().set(key, value);
	}

	/**
	 * 向缓存中压入KEY-Value键值对并设置过期时间
	* @Title: set  
	* @Description: 向缓存中压入KEY-Value键值对并设置过期时间，若KEY已经存在，则覆盖
	* @param key
	* @param value
	* @param expired void    返回类型  
	* @throws
	 */
	@Override
	public void set(String key, Object value, int expired)
	{
		template.opsForValue().set(key, value, expired,TimeUnit.SECONDS);		
	}

	@Override
	public long incr(String key,long num) {
		long lo = template.opsForValue().increment(key,num);
		return lo;
	}
	
	@Override
	public boolean hSetnx(String key, String hashKey, Object value)
	{
		return template.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * 移除键Key的值
	* @Title: remove  
	* @Description: 移除键Key的值
	* @param key void    返回类型  
	* @throws
	 */
	@Override
	public void remove(String key)
	{
		template.delete(key);
	}

	/**
	 * 获取键为Key的数据，若不存在返回null
	* @Title: get  
	* @Description: 获取键为Key的数据，若不存在返回null 
	* @param key
	* @return Object    返回类型  
	* @throws
	 */
	@Override
	public Object get(String key)
	{
		return template.opsForValue().get(key);
	}
	
	
	@Override
	public void expire(String key, long timeout, TimeUnit unit)
	{
		template.expire(key, timeout, unit);
	}
	@Override
	public void expireAt(String key, Date date)
	{
		template.expireAt(key, date);
	}
	
	@Override
	public Long getExpire(String key)
	{
		return template.getExpire(key);
	}
	
	/**
	 * 向队列Key的最左边插入数据value
	* @Title: lLeftPush  
	* @Description:  向队列Key的最左边插入数据value 
	* @param key
	* @param value void    返回类型  
	* @throws
	 */
	@Override
	public void lLeftPush(String key, Object value)
	{
		template.opsForList().leftPush(key, value);
	}

	/**
	 * 向队列Key最右边插入数据value 
	* @Title: lRightPush  
	* @Description: 向队列Key最右边插入数据value 
	* @param key
	* @param value
	* @throws
	 */
	@Override
	public void lRightPush(String key, Object value)
	{
		 template.opsForList().rightPush(key, value);
	}

	/**
	 * 返回队列Key的最左边数据并删除
	* @Title: lLeftPop  
	* @Description: 返回队列Key的最左边数据并删除
	* @param key 
 	* @return Object  返回类型
	* @throws
	 */
	@Override
	public Object lLeftPop(String key)
	{
		return template.opsForList().leftPop(key);
	}

	/**
	 *返回队列Key的最右边数据并删除
	* @Title: lRightPop  
	* @Description:  返回队列Key的最右边数据并删除
	* @param key
	* @return Object  
	* @throws
	 */
	@Override
	public Object lRightPop(String key)
	{
		return template.opsForList().rightPop(key);
	}

	/**
	 * 从队列Key获取索引为index的数据，若不存在，返回Null
	* @Title: lGet  
	* @Description: 从队列Key获取索引为index的数据，若不存在，返回Null 
	* @param key
	* @param index
	* @return Object    返回类型  
	* @throws
	 */
	@Override
	public Object lGet(String key, long index)
	{
		return template.opsForList().index(key, index);
	}

	/**
	 * 获取队列Key的所有数据，并返回List
	* @Title: lList  
	* @Description: 获取队列Key的所有数据，并返回List  
	* @param key
	* @return List    返回类型  
	* @throws
	 */
	@Override
	public List<Object> lList(String key)
	{
		return template.opsForList().range(key, 0, this.lCount(key)-1);
	}

	/**
	 * 从队列Key的获取从lIndex到rIndex的数据，并返回List
	* @Title: lRange  
	* @Description: 从队列Key的获取从lIndex到rIndex的数据，并返回List 
	* @param key
	* @param lIndex
	* @param rIndex
	* @return List    返回类型  
	* @throws
	 */
	@Override
	public List<Object> lRange(String key, int lIndex, int rIndex)
	{  
		return template.opsForList().range(key,lIndex,rIndex);
	}

	/**
	 * 获取队列Key的长度
	* @Title: lCount  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param key
	* @return int    返回类型  
	* @throws
	 */
	@Override
	public long lCount(String key)
	{  
		return template.opsForList().size(key);
	}
	@Override
	public Long lRemove(String key, long i, Object value)
	{
		return template.opsForList().remove(key, i, value);
	}
	
	/**
	 * 保留list设定区间元素，移除设定区间外元素
	 * @author tomtang  
	 * @version 1.0
	 * @date 2016年3月29日 下午4:44:57
	 */
	@Override
	public void lTrim(String key, long start, long end) {
		template.opsForList().trim(key, start, end);
	}

	/**
	 * 向hash表Key中插入 hashKey-value的键值对，若hashkey存在，则覆盖
	* @Title: hPush  
	* @Description: 向hash表Key中插入 hashKey-value的键值对，若hashkey存在，则覆盖
	* @param key
	* @param hashKey
	* @param value void    返回类型  
	* @throws
	 */
	@Override
	public void hPush(String key, Object hashKey, Object value)
	{ 
		template.opsForHash().put(key, hashKey, value);
	}

	/**
	 * 获取并移除hash表Key中键 hashKey的值
	* @Title: hPush  
	* @Description:  获取并移除hash表Key中键 hashKey的值
	* @param key
	* @param hashKey
	* @param value 
	* @return Object   返回类型  
	* @throws
	 */
	@Override
	public Object hPop(String key, Object hashKey)
	{
		Object result=null;
		if(template.opsForHash().hasKey(key, hashKey))
		{
			result=template.opsForHash().get(key, hashKey);
			template.opsForHash().delete(key, hashKey);
		}
		return result;
	}

	/**
	 * 获取hash表Key 中键为hashKey的值
	* @Title: hGet  
	* @Description: 获取hash表Key 中键为hashKey的值 
	* @param key
	* @param hashKey
	* @return Object    返回类型  
	* @throws
	 */
	@Override
	public Object hGet(String key, Object hashKey)
	{ 
		if(template.opsForHash().hasKey(key, hashKey))
			return template.opsForHash().get(key, hashKey);
		else
			return null;
	}

	/**
	 * 判断是否存在
	 *
	 * @param key
	 * @param hashKey
	 * @return
	 */
	@Override
	public Boolean hExist(String key, Object hashKey) {
		return template.opsForHash().hasKey(key, hashKey);
	}

	/**
	 * 获取hash中key的长度
	* @Title: hCount  
	* @Description:  获取hash中key的长度
	* @param key
	* @return long    返回类型  
	* @throws
	 */
	@Override
	public long hCount(String key)
	{
		return template.opsForHash().size(key);
	}

	/**
	 * 获取hash表Key的所有值并返回Map
	* @Title: hMap  
	* @Description: 获取hash表Key的所有值并返回Map
	* @param key
	* @return Map<Object,Object>    返回类型  
	* @throws
	 */
	@Override
	public Map<Object,Object>  hMap(String key)
	{
		return template.opsForHash().entries(key);
	}

	/**
	 * hash表Key的键为hashkey的数据增加delta
	* @Title: hIncrement  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param key
	* @param hashKey
	* @param delta
	* @return double    返回类型  
	* @throws
	 */
	@Override
	public double hIncrement(String key, Object hashKey, double delta)
	{  
		return template.opsForHash().increment(key, hashKey, delta);
	}

	/**
	 * hash表Key的键为hashkey的数据增加delta
	* @Title: hIncrement  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param key
	* @param hashKey
	* @param delta
	* @return int    返回类型  
	* @throws
	 */
	@Override
	public Long hIncrement(String key, Object hashKey, int delta)
	{ 
		return template.opsForHash().increment(key, hashKey, delta);
	}
	@Override
	public void hDelete(String key,Object... hashKeys)
	{
		template.opsForHash().delete(key, hashKeys);
	}
	
	@Override
	public void hPutAll(String  key,Map<String,Object>  map)
	{
		template.opsForHash().putAll(key, map);
	}
	 
	 
	@Override
	public boolean sIsMember(String key, Object value)
	{ 
		return template.opsForSet().isMember(key, value); 
	}


	@Override
	public long sAdd(String key, Object... values)
	{
		return template.opsForSet().add(key, values);
	}

	 
	@Override
	public long sRemove(String key, Object... values)
	{
		return template.opsForSet().remove(key, values);
	}

	 
	@Override
	public long sCount(String key)
	{  
		return template.opsForSet().size(key);
	}

	 
	@Override
	public Object sPop(String key)
	{  
		return template.opsForSet().pop(key);
	}
	
	@Override
	public Boolean zAdd(String key,Object value,double score)
	{
		 
		return template.opsForZSet().add(key,value,score);
	}
	@Override
	public Set<Object> zRange(String key,Long start,Long end)
	{ 
		return template.opsForZSet().range(key, start, end);
	}

	@Override
	public Long zRemove(String key,Object... values)
	{
		return template.opsForZSet().remove(key, values);
	}
	
	@Override
	public Long zRank(String key,Object value)
	{
		return template.opsForZSet().rank(key, value);
	}
	
	@Override
	public Set<Object> zRevRank(String key, long start, long end) {
		return template.opsForZSet().reverseRange(key, start, end);
	}
	
	@Override
	public Long zCard(String key)
	{
		return template.opsForZSet().zCard(key);
	}
	
	@Override
	public long zRemoveRange(String key, long start, long end) {
		
		return template.opsForZSet().removeRange(key, start, end);
	}

	/* (非 Javadoc)  
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param userNo  
	* @see com.eshare.core.cache.ICacheTemplate#delete(java.lang.Object)  
	*/
	@Override
	public void delete(String key)
	{
		 template.delete(key); 
	}

}
