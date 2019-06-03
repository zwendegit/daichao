package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.admin.AdminUserInput;
import com.daichao.admin.input.admin.AdminUserPageInput;
import com.daichao.admin.service.AdminUserService;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="AdminUserController",description="用户管理")
@RequestMapping("/admin")
@RestController
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@ApiOperation(value = "登陆", notes = "登陆")
	@RequestMapping(value="/adminUserLogin",method = RequestMethod.POST)
	public ResultOutput adminUserLogin(AdminUserInput input,HttpServletRequest request){
        return adminUserService.adminUserLogin(input,request);
	}
	
	@ApiOperation(value = "用户列表", notes = "分页")
	@RequestMapping(value="/adminUserPage",method = RequestMethod.POST)
	public ResultOutput adminUserPage(AdminUserPageInput input){
        Page<DcAdminUser> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcAdminUser> list=adminUserService.queryAdminUserList(input);
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "用户编辑", notes = "编辑")
	@RequestMapping(value="/adminUserSave",method = RequestMethod.POST)
	public ResultOutput adminUseradd(DcAdminUser input){
		return adminUserService.adminUserSave(input);
	}

	@ApiOperation(value = "用户角色列表", notes = "列表")
	@RequestMapping(value="/adminUserRoleList",method = RequestMethod.POST)
	public ResultOutput adminUserRoleList(HttpServletRequest request,Integer userId){
		return new ResultOutput(adminUserService.adminUserRoleList(userId)) ;
	}
	
	@ApiOperation(value = "用户角色编辑", notes = "编辑")
	@RequestMapping(value="/adminUserRoleUpdate",method = RequestMethod.POST)
	public ResultOutput adminUserRoleUpdate(HttpServletRequest request,Integer userId,String roleIds){
		return new ResultOutput(adminUserService.adminUserRoleUpdate(userId,roleIds)) ;
	}
}
