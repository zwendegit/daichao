package com.daichao.admin.input.advert;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AdvertPageInput extends PageInput{

	@ApiParam("位置 1 首页")
	private Integer location;
}
