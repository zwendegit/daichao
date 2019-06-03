package com.daichao.admin.input.product.statistics;


import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class StatisticsPageInput extends PageInput{
 
	@ApiParam("产品名称")
	private String productName;
	@ApiParam("时间")
	private String time;
}
