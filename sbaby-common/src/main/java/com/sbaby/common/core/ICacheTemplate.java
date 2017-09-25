package com.sbaby.common.core;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface ICacheTemplate {
	/**
	 * 向缓存中压入KEY-Value键值对 @Title: set @Description:
	 * 向缓存中压入KEY-Value键值对，若KEY已经存在，则覆盖 @param key @param value void 返回类型 @throws
	 */
	void set(String key, Object value);

	/**
	 * 向缓存中KEY-Value原子性增1 @Title: set @param key @throws
	 */
	long incr(String key, long num);

	/**
	 * 向缓存中压入KEY-Value键值对并设置过期时间 @Title: set @Description:
	 * 向缓存中压入KEY-Value键值对并设置过期时间，若KEY已经存在，则覆盖 @param key @param value @param
	 * expired void 返回类型 @throws
	 */
	void set(String key, Object value, int expired);

	/**
	 * 移除键Key的值 @Title: remove @Description: 移除键Key的值 @param key void
	 * 返回类型 @throws
	 */
	void remove(String key);

	/**
	 * 获取键为Key的数据，若不存在返回null @Title: get @Description:
	 * 获取键为Key的数据，若不存在返回null @param key @return Object 返回类型 @throws
	 */
	Object get(String key);

	// 队列操作 //

	/**
	 * 向队列Key的最左边插入数据value @Title: lLeftPush @Description:
	 * 向队列Key的最左边插入数据value @param key @param value void 返回类型 @throws
	 */
	void lLeftPush(String key, Object value);

	/**
	 * 向队列Key最右边插入数据value @Title: lRightPush @Description:
	 * 向队列Key最右边插入数据value @param key @param value @throws
	 */
	void lRightPush(String key, Object value);

	/**
	 * 返回队列Key的最左边数据并删除 @Title: lLeftPop @Description: 返回队列Key的最左边数据并删除 @param
	 * key @return Object 返回类型 @throws
	 */
	Object lLeftPop(String key);

	/**
	 * 返回队列Key的最右边数据并删除 @Title: lRightPop @Description: 返回队列Key的最右边数据并删除 @param
	 * key @return Object @throws
	 */
	Object lRightPop(String key);

	/**
	 * 从队列Key获取索引为index的数据，若不存在，返回Null @Title: lGet @Description:
	 * 从队列Key获取索引为index的数据，若不存在，返回Null @param key @param index @return Object
	 * 返回类型 @throws
	 */
	Object lGet(String key, long index);

	/**
	 * 获取队列Key的所有数据，并返回List @Title: lList @Description:
	 * 获取队列Key的所有数据，并返回List @param key @return List 返回类型 @throws
	 */
	List<Object> lList(String key);

	/**
	 * 从队列Key的获取从lIndex到rIndex的数据，并返回List @Title: lRange @Description:
	 * 从队列Key的获取从lIndex到rIndex的数据，并返回List @param key @param lIndex @param
	 * rIndex @return List 返回类型 @throws
	 */
	List<Object> lRange(String key, int lIndex, int rIndex);

	/**
	 * 获取队列Key的长度 @Title: lCount @Description: TODO(这里用一句话描述这个方法的作用) @param
	 * key @return int 返回类型 @throws
	 */
	long lCount(String key);

	Long lRemove(String key, long i, Object value);
	// Hash 操作 //

	/**
	 * 向hash表Key中插入 hashKey-value的键值对，若hashkey存在，则覆盖 @Title: hPush @Description:
	 * 向hash表Key中插入 hashKey-value的键值对，若hashkey存在，则覆盖 @param key @param
	 * hashKey @param value void 返回类型 @throws
	 */
	void hPush(String key, Object hashKey, Object value);

	/**
	 * 获取并移除hash表Key中键 hashKey的值 @Title: hPush @Description: 获取并移除hash表Key中键
	 * hashKey的值 @param key @param hashKey @return Object 返回类型 @throws
	 */
	Object hPop(String key, Object hashKey);

	/**
	 * 获取hash表Key 中键为hashKey的值 @Title: hGet @Description: 获取hash表Key
	 * 中键为hashKey的值 @param key @param hashKey @return Object 返回类型 @throws
	 */
	Object hGet(String key, Object hashKey);

	/**
	 * 判断是否存在
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	Boolean hExist(String key, Object hashKey);

	/**
	 * 获取hash中key的长度 @Title: hCount @Description: 获取hash中key的长度 @param
	 * key @return long 返回类型 @throws
	 */
	long hCount(String key);

	/**
	 * 获取hash表Key的所有值并返回Map @Title: hMap @Description:
	 * 获取hash表Key的所有值并返回Map @param key @return Map<Object,Object> 返回类型 @throws
	 */
	Map<Object, Object> hMap(String key);

	/**
	 * hash表Key的键为hashkey的数据增加delta @Title: hIncrement @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param key @param hashKey @param delta @return
	 * double 返回类型 @throws
	 */
	double hIncrement(String key, Object hashKey, double delta);

	/**
	 * hash表Key的键为hashkey的数据增加delta @Title: hIncrement @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param key @param hashKey @param delta @return Long
	 * 返回类型 @throws
	 */
	Long hIncrement(String key, Object hashKey, int delta);

	void hDelete(String key, Object... hashKeys);

	void hPutAll(String key, Map<String,Object> map);

	boolean sIsMember(String key, Object value);

	long sAdd(String key, Object... values);

	long sRemove(String key, Object... values);

	long sCount(String key);

	Object sPop(String key);

	/**
	 * @Title: zAdd @Description: TODO(这里用一句话描述这个方法的作用) @param key @param
	 * value @param score @return Boolean 返回类型 @throws
	 */
	Boolean zAdd(String key, Object value, double score);

	/**
	 * @Title: zRange @Description: TODO(这里用一句话描述这个方法的作用) @param key @param
	 * start @param end @return Set 返回类型 @throws
	 */
	Set<Object> zRange(String key, Long start, Long end);

	/**
	 * @Title: zRemove @Description: TODO(这里用一句话描述这个方法的作用) @param key @param
	 * values @return Long 返回类型 @throws
	 */
	Long zRemove(String key, Object... values);

	/**
	 * @Title: zRank @Description: TODO(这里用一句话描述这个方法的作用) @param key @param
	 * value @return Long 返回类型 @throws
	 */
	Long zRank(String key, Object value);

	Set<Object> zRevRank(String key, long start, long end);

	long zRemoveRange(String key, long start, long end);

	/**
	 * @Title: expire @Description: TODO(这里用一句话描述这个方法的作用) @param key @param
	 * timeout @param unit void 返回类型 @throws
	 */
	void expire(String key, long timeout, TimeUnit unit);

	/**
	 * @Title: zCard @Description: TODO(这里用一句话描述这个方法的作用) @param key @return Long
	 * 返回类型 @throws
	 */
	Long zCard(String key);

	/**
	 *
	 * @param key
	 */
	void delete(String key);

	/**
	 *
	 * @param key
	 * @param date
	 */
	void expireAt(String key, Date date);

	Long getExpire(String key);

	/**
	 * 保留list设定区间元素，移除设定区间外元素
	 * 
	 * @author tomtang
	 * @version 1.0
	 * @date 2016年3月29日 下午4:44:57
	 */
	void lTrim(String key, long start, long end);

	boolean hSetnx(String key, String hashKey, Object value);
}
