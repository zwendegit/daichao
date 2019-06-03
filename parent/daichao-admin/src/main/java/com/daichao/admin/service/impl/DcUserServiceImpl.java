package com.daichao.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.service.DcUserService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;
import com.daichao.dao.user.DcUserMapper;

@Service
public class DcUserServiceImpl implements DcUserService{

	@Autowired
	private DcUserMapper dcUserMapper;
	@Override
	public ResultOutput saveUser(DcUser user) {
		dcUserMapper.insertSelective(user);
		return new ResultOutput();
	}

}
