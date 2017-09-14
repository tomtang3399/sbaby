package com.sbaby.common.entity;

import java.io.Serializable;

public class TestDubboEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private Integer age;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
