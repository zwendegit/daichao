package com.daichao.admin.input.admin;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AdminRoleInput extends BaseInput{

	@ApiParam("角色名称")
	private String roleName;
	@ApiParam("状态 1有效 0失效")
	private Integer status;
}
