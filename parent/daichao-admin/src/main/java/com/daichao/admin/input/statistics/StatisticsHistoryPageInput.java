package com.daichao.admin.input.statistics;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class StatisticsHistoryPageInput extends PageInput{

	@ApiParam("渠道名称")
	private String code;
	@ApiParam("日期")
	private String time;
}
