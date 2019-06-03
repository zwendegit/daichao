package com.daichao.admin.output;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StatisticsOutput {

	private Integer channelUvCount;//落地页uv
	private Integer registCountDay;//预注册
	private Integer loginCountDay;//应用内uv
	private Integer registLoginCountDay;//应用内注册
	private Integer uvSumDay;//贷款uv
	private BigDecimal moneySum;//综合收入
	private BigDecimal zcMoney;//预计支出
	private BigDecimal jsPrice;//结算单价
	private Integer threshold;//阙值
	private BigDecimal deductionRate;//扣量因子
	private Integer registCount;//结算数量
	private Integer registDeductionCount;//实际结算
	private BigDecimal roi;//roi
	private BigDecimal channelPrice;//结算单价
	
	private String codeName;
	private String code;
	
}
