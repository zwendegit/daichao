package com.daichao.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.user.UserPageInput;
import com.daichao.admin.service.UserService;
import com.daichao.bean.user.DcUser;
import com.daichao.dao.user.DcUserMapper;
@Service
public class UserServiceImple implements UserService {

	@Autowired
	private DcUserMapper dcUserMapper;
	@Override
	public List<DcUser> userList(UserPageInput input) {
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(input.getMobile())) map.put("mobile", input.getMobile());
		if(StringUtils.isNotEmpty(input.getRegistChannel())) map.put("registChannel", input.getRegistChannel());
		if(StringUtils.isNotEmpty(input.getLoginChannel())) map.put("loginChannel", input.getLoginChannel());
		if(StringUtils.isNotEmpty(input.getType())) {
			map.put("type", input.getType());
			map.put("startTime", StringUtils.isNotEmpty(input.getStartTime())?input.getStartTime():null);
			map.put("endTime", StringUtils.isNotEmpty(input.getEndTime())?input.getEndTime():null);
		}
		return dcUserMapper.userList(map);
	}

}
