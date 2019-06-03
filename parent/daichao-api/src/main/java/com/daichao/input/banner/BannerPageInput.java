package com.daichao.input.banner;

import com.daichao.bean.input.PageInput;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class BannerPageInput extends PageInput{

	@ApiParam("名称")
	private String name;
	@ApiParam("链接")
	private String linkUrl;
	@ApiParam("状态1启用,0禁用")
	private Integer status;
}
