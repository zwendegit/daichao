package com.daichao.admin.input.system;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class SystemConfigListInput extends BaseInput{

	@ApiParam("类型 1： h5标识 0:其他")
	private Integer type;
	
	@ApiParam("标识")
    private String code;
}
