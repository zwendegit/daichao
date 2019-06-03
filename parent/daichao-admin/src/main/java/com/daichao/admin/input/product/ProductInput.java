package com.daichao.admin.input.product;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class ProductInput extends PageInput{

	@ApiParam("产品名称")
	private String name;
	@ApiParam("产品状态")
	private Integer status;
}
