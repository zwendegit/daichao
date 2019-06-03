package com.daichao.admin.input.advert;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AdvertInput extends BaseInput{

	@ApiParam("产品ids")
	private String productIds;
	@ApiParam("位置： 1 首页")
	private Integer location;
}
