package com.daichao.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.user.UserPageInput;
import com.daichao.admin.service.UserService;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="UserController",description="前台用户管理")
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "用户列表", notes = "分页")
	@RequestMapping(value="/userPage",method = RequestMethod.POST)
	public ResultOutput adminUserPage(UserPageInput input){
        Page<DcUser> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcUser> list=userService.userList(input);
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
}
