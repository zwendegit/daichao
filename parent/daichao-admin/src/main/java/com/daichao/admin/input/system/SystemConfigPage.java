package com.daichao.admin.input.system;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class SystemConfigPage extends PageInput{

	@ApiParam("类型 1： h5标识 0:其他")
	private Integer type;
	
	@ApiParam("1有效 0无效")
    private Integer status;
}
