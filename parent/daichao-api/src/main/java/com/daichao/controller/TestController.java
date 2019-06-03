package com.daichao.controller;

import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcUser;
import com.daichao.service.DcUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;




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
//		user.setUsername("18968882585");
//		user.setPassword(MD5.MD5Encode("a111111"));
//		user.setPhone("18968882585");
		return dcUserService.saveUser(user);
	}
}
