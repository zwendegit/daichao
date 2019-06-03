package com.daichao.admin.input.popup;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class PopupInput extends PageInput{

	@ApiParam("名称")
	private String name;
	@ApiParam("状态 1有效 0失效")
	private String status;
	@ApiParam("链接地址")
	private String url;
}
