package com.daichao.admin.input.user;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class UserPageInput extends PageInput{

	@ApiParam("手机号")
	private String mobile;
	@ApiParam("注册渠道")
	private String registChannel;
	@ApiParam("登录渠道")
	private String loginChannel;
	@ApiParam("类型 1 注册 2登录")
	private String type;
	@ApiParam("开始时间")
	private String startTime;
	@ApiParam("结束时间")
	private String endTime;
}
