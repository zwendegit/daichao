package com.daichao.admin.input.channel;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class ChannelPageInput extends PageInput{

	@ApiParam("渠道名称")
	private String name;
	@ApiParam("渠道编号")
	private String code;
	@ApiParam("1 h5 2 app 3pc")
	private Integer type;
	@ApiParam("1 上架 0下架")
	private Integer status;
}
