package com.daichao.admin.input.regist;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class RegistStatisticsPageInput extends PageInput{

	@ApiParam("渠道编码")
	private String code;
	@ApiParam("时间 ")
	private String time;
	
}
