package com.sbaby.common.util;

import org.springframework.stereotype.Service;

import com.sbaby.common.core.JsonMapper;

@Service
public class SbabyCommonService {

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();
}
