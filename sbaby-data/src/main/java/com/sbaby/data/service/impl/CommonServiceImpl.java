package com.sbaby.data.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.City;
import com.sbaby.common.entity.db.nosqlDB.elasticSearch.Province;
import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.service.EsCityReponstityService;
import com.sbaby.data.reponstity.nosqlDB.elasticsearch.service.EsProvinceReponstityService;
import com.sbaby.data.reponstity.relationalDB.service.UserReponstityService;
import com.sbaby.data.service.ICommonService;

@Service
public class CommonServiceImpl implements ICommonService {

	@Autowired
	private UserReponstityService userReponstityService;

	@Autowired
	private EsProvinceReponstityService esProvinceReponstityService;

	@Autowired
	private EsCityReponstityService esCityReponstityService;

//	@Autowired
//	private ElasticsearchTemplate template;
	
	/* 搜索模式 */
	String SCORE_MODE_SUM = "sum"; // 权重分求和模式
	Float MIN_SCORE = 10.0F; // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

	public List<User> findAll() {

		/**
		 * spring-data-jpa-mysql 复杂查询可直接使用sql语句
		 */
		@SuppressWarnings("unused")
		List<User> users = userReponstityService.userReponstity.findByUserName("tomtang");

		System.out.println("-----ok-----");

		return userReponstityService.findAll();
	}

	/**
	 * spring-data-jpa-mysql 封装方法 ，开启事务时，不能try...cache...,或在cache内部主动抛出runTimeException异常
	 */
	@Transactional
	public User saveUser() {
		User user = new User();
		String id = UUID.randomUUID().toString();
		user.setId(id);
		user.setAge(100);
		user.setUserName("jim");
		userReponstityService.saveAndFlush(user);
		return user;
	}

	/**
	 * spring-data-jpa-elasticSearch 封装方法
	 */
	public Province saveprovince() {
		Province province = new Province();
		province.setCreateTime(new Date());
		province.setId(UUID.randomUUID().toString());
		province.setProvinceName("四川");
		
		
		 City a = new City();
		 a.setCityName("成都");
		 a.setId(UUID.randomUUID().toString());

		 City b = new City();
		 b.setCityName("资阳");
		 b.setId(UUID.randomUUID().toString());
		
		 List<City> citys = Lists.newArrayList();
		 citys.add(a);
		 citys.add(b);
		
		 province.setCity(citys);
		 esProvinceReponstityService.saveAndFlush(province);

		// 构建搜索查询
		// SearchQuery searchQuery = getCitySearchQuery(1,5,"四川");
		//
		// List<Province> provinces =
		// esProvinceReponstityService.searchQuery(searchQuery);
		//
		// System.out.println("---"+JSONArray.toJSONString(provinces));
//		List<Province> provinces = moreLikeByContent("四川");
//		System.out.println("===ok===");
		return province;
	}

	/**
	 * 操作es数据库方式1：利用ElasticsearchTemplate
	 * 
	 * @param content
	 * @return
	 */
//	public List<Province> moreLikeByContent(String content) {
//		SearchResponse response = template.getClient().prepareSearch("province").setTypes("province")
//				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//				.setQuery(QueryBuilders.matchQuery("provinceName", content)) // 全文检索
//				.addHighlightedField("provinceName")// 高亮，可以设置前缀和后缀
//				.setFrom(0).setSize(10).setExplain(true)// 分页
//				.addSort(new ScoreSortBuilder().order(SortOrder.DESC))// 排序
//				.setTrackScores(true)// 获取得分
//				.execute().actionGet();
//
//		List<Province> chunk = new ArrayList<Province>();
//		for (SearchHit searchHit : response.getHits()) {
//			if (response.getHits().getHits().length <= 0) {
//				return null;
//			}
//			System.out.println(searchHit.getScore());
//			Province test = new Province();
//			test.setId(searchHit.getId());
//			test.setProvinceName(searchHit.getHighlightFields().get("provinceName").fragments()[0].toString());
//			chunk.add(test);
//		}
//		return chunk;
//	}
//
//	/**
//	 * 操作es数据库方式2：利用spring-data-jpa,定义规范的查询方法，spring-data-jpa自动解析
//	 * 参考：https://es.yemengying.com/4/4.4/4.4.2.html
//	 * 
//	 * @param content
//	 * @return
//	 */
//	public List<Province> findByProvinceNameAsc(String provinceName) {
//		List<Province> provinces = esProvinceReponstityService.findByProvinceNameAsc(provinceName);
//		System.out.println("------ok------");
//		return provinces;
//	}

}
