package com.daichao.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.admin.AdminRoleInput;
import com.daichao.admin.service.AdminRoleService;
import com.daichao.bean.admin.DcAdminRole;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.admin.DcAdminRoleMapper;
@Service
public class AdminRoleServiceImpl implements AdminRoleService{

	@Autowired
	private DcAdminRoleMapper dcAdminRoleMapper;
	@Override
	public List<DcAdminRole> queryAdminRoleList(AdminRoleInput input) {
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotEmpty(input.getRoleName())) {
			map.put("name", input.getRoleName());
		}
		if(input.getStatus()!=null) {
			map.put("status", input.getStatus());
		}
		return dcAdminRoleMapper.queryAdminRoleList(map);
	}
	@Override
	public ResultOutput adminRoleUpdate(DcAdminRole role) {
		if(role.getId()==null) dcAdminRoleMapper.insert(role);
		else dcAdminRoleMapper.updateByPrimaryKeySelective(role);
		return new ResultOutput();
	}

}
