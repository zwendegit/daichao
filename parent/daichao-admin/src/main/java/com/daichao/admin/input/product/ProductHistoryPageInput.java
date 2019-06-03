package com.daichao.admin.input.product;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class ProductHistoryPageInput extends PageInput{

	@ApiParam("产品ID")
	private Integer productId;
	@ApiParam("时间")
	private String time;
}
