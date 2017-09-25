package com.sbaby.im.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbaby.im.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="/demo", method=RequestMethod.GET)
	public String demo() {
		demoService.saveCache();
		return "---------ok-------";
	}
}