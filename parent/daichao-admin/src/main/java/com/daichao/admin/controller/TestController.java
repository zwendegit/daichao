package com.daichao.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.service.DcUserService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




@Api(value="TestController",description="测试接口描述")
@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private DcUserService dcUserService;
	
	@ApiOperation(value = "test", notes = "get请求，test")
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public ResultOutput updateUnReadMsg(HttpServletRequest req){
		DcUser user=new DcUser();
		return dcUserService.saveUser(user);
	}
}
