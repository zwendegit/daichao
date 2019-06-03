package com.daichao.admin.input.product;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class ThirdTagInput extends BaseInput{

	@ApiParam("名称")
	private String name;
	@ApiParam("状态 1上架 0下架")
	private String status;
	@ApiParam("产品id")
	private Integer productId;
	@ApiParam("标签id")
	private Integer tagId;
}
