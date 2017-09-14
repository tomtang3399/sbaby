package com.sbaby.common.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.sbaby.common.entity.TestDubboEntry;

/**
 * dubbox内部模块调用序列化类,采用了Kryo序列化方式
 * @author Administrator
 *
 */
public class DubboSerializationOptimizer implements SerializationOptimizer {

	@SuppressWarnings("rawtypes")
	@Override
	public Collection<Class> getSerializableClasses() {
		// TODO Auto-generated method stub
		List<Class> list = new ArrayList<Class>();
		list.add(TestDubboEntry.class);
		return list;
	}

}
