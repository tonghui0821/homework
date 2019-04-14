package com.gupao.demo.service.impl;

import com.gupao.demo.service.IDemoService;
import com.gupao.mvcframework.annotation.GPService;


/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	@Override
	public String get(String name) {
		return "My name is " + name;
	}

}
