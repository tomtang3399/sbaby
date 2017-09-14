package com.sbaby.middleware.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login() {
		return loginService.loginSendMsg();
	}
	
	@RequestMapping(value="/findUsers",method=RequestMethod.GET)
	public String findUsers() {
		return loginService.findAllUsers();
	}
}
