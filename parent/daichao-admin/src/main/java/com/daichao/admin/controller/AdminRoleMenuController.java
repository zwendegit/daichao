package com.daichao.admin.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.service.AdminRoleMenuService;
import com.daichao.bean.admin.DcAdminMenu;
import com.daichao.bean.output.ResultOutput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="AdminRoleMenuController",description="角色菜单管理")
@RequestMapping("/role")
@RestController
public class AdminRoleMenuController {

	@Autowired
	private AdminRoleMenuService adminRoleMenuService;
	@ApiOperation(value = "角色菜单列表", notes = "列表")
	@RequestMapping(value="/adminRoleMenuList",method = RequestMethod.POST)
	public ResultOutput adminRoleMenuList(Integer roleId){
		return new ResultOutput(adminRoleMenuService.queryAdminRoleMenuListByRoleId(roleId));
	}
	
	@ApiOperation(value = "角色菜单编辑", notes = "编辑")
	@RequestMapping(value="/adminRoleMenuUpdate",method = RequestMethod.POST)
	public ResultOutput adminRoleMenuUpdate(@RequestParam Integer roleId,String ids){
		return new ResultOutput(adminRoleMenuService.adminRoleMenuUpdate(roleId, ids));
	}
	
	@ApiOperation(value = "菜单列表", notes = "列表")
	@RequestMapping(value="/adminMenuList",method = RequestMethod.POST)
	public ResultOutput adminMenuList(){
		return new ResultOutput(adminRoleMenuService.adminMenuList());
	}
	
	@ApiOperation(value = "菜单编辑", notes = "编辑")
	@RequestMapping(value="/menuSave",method = RequestMethod.POST)
	public ResultOutput menuSave(DcAdminMenu menu){
		return adminRoleMenuService.menuSave(menu);
	}
}
