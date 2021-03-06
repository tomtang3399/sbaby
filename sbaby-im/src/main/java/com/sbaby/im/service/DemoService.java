package com.sbaby.im.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbaby.common.core.CacheTemplate;

@Service
public class DemoService {

	@Autowired
	private CacheTemplate cacheTemplate;
	
	public void saveCache() {
		cacheTemplate.set("demo", "ok");
	}
}
