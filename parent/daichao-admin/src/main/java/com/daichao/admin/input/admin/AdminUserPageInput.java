package com.daichao.admin.input.admin;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AdminUserPageInput extends PageInput{

	@ApiParam("电话")
	private String mobile;
	@ApiParam("用户名")
	private String userName;
	@ApiParam("公司")
	private String company;
	@ApiParam("1 产品 2渠道 3内部用户")
	private Integer userType;
}
