package com.daichao.input.product;

import com.daichao.bean.input.PageInput;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ProductInput extends PageInput{

	@ApiParam("产品状态")
	private Integer status = 1;
	@ApiParam("产品ids(tag按钮点击返回产品列表)")
	private String productIds;
}
