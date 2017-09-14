package com.sbaby.middleware.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbaby.middleware.service.impl.LoginServiceImpl;

@RestController
@RequestMapping("/v1")
public class BaseController {

	@Autowired
	protected LoginServiceImpl loginService;
	
}
