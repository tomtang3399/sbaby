package com.sbaby.common.entity.db.nosqlDB.elasticSearch;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "city", type = "city", shards = 1, replicas = 1)
public class City {

	private String id;
	
	private String cityName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
