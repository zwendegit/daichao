package com.daichao.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.admin.AdminRoleInput;
import com.daichao.admin.service.AdminRoleService;
import com.daichao.bean.admin.DcAdminRole;
import com.daichao.bean.output.ResultOutput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="AdminRoleController",description="角色管理")
@RequestMapping("/role")
@RestController
public class AdminRoleController {

	@Autowired
	private AdminRoleService adminRoleService;
	@ApiOperation(value = "角色列表", notes = "列表")
	@RequestMapping(value="/adminRoleList",method = RequestMethod.POST)
	public ResultOutput adminRoleList(AdminRoleInput input){
        List<DcAdminRole> list=adminRoleService.queryAdminRoleList(input);
		return new ResultOutput(list);
	}
	
	@ApiOperation(value = "角色编辑", notes = "编辑")
	@RequestMapping(value="/adminRoleUpdate",method = RequestMethod.POST)
	public ResultOutput adminRoleUpdate(DcAdminRole input){
		return adminRoleService.adminRoleUpdate(input);
	}
	
	
}
