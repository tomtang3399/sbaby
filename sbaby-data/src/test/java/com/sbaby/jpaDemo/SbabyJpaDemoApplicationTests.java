package com.sbaby.jpaDemo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbaby.common.entity.db.relationalDB.User;
import com.sbaby.data.service.ICommonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SbabyJpaDemoApplicationTests {

	@Autowired
	private ICommonService userService;
	
	@Test
	public void contextLoads() {
		@SuppressWarnings("unused")
		List<User> users = userService.findAll();
		System.out.println("-----ok-----");
	}

}
