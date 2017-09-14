package com.sbaby.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务
@SpringBootApplication
@EntityScan(basePackages={
		"com.sbaby.common"
		}) //扫描并将该包下的实体类并交由该项目spring托管
public class SbabyJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbabyJpaDemoApplication.class, args);
	}
}
